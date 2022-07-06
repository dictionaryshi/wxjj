package com.wx.service;

import com.scy.core.format.MessageUtil;
import com.scy.core.thread.ThreadPoolUtil;
import com.scy.netty.job.annotation.Job;
import com.wx.domain.job.entity.JobInfoEntity;
import com.wx.domain.job.entity.JobRegistryEntity;
import com.wx.domain.job.service.JobInfoDomainService;
import com.wx.domain.job.service.JobRegistryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : shichunyang
 * Date    : 2022/6/29
 * Time    : 2:59 下午
 * ---------------------------------------
 * Desc    : JobFacade
 */
@Slf4j
@Service
public class JobFacade {

    public static final ThreadPoolExecutor JOB_TASK_FAST = ThreadPoolUtil.getThreadPool("job-task-fast", 10, 200, 1000);

    public static final ThreadPoolExecutor JOB_TASK_SLOW = ThreadPoolUtil.getThreadPool("job-task-slow", 10, 200, 1000);

    private volatile long timeWindow = System.currentTimeMillis() / 60_000;

    private final ConcurrentMap<Integer, AtomicInteger> jobTimeoutCountMap = new ConcurrentHashMap<>();

    @Autowired
    private JobRegistryDomainService jobRegistryDomainService;

    @Autowired
    private JobInfoDomainService jobInfoDomainService;

    public List<JobInfoEntity> queryscheduleJobs(long maxNextTime, int limit) {
        return jobInfoDomainService.queryscheduleJobs(maxNextTime, limit);
    }

    public void registry(JobRegistryEntity jobRegistryEntity) {
        jobRegistryDomainService.registry(jobRegistryEntity);
    }

    public void registryRemove(JobRegistryEntity jobRegistryEntity) {
        jobRegistryDomainService.registryRemove(jobRegistryEntity);
    }

    @Job(value = "JobService-helloWord", init = "init", destroy = "destroy")
    public void helloWord() {
        log.info("helloWord");
    }

    public void init() {
        log.info("init");
    }

    public void destroy() {
        log.info("destroy");
    }

    public void updateNextTime(JobInfoEntity jobInfoEntity) {
        JobInfoEntity updateJobInfoEntity = new JobInfoEntity();
        updateJobInfoEntity.setId(jobInfoEntity.getId());
        updateJobInfoEntity.setTriggerStatus(jobInfoEntity.getTriggerStatus());
        updateJobInfoEntity.setTriggerLastTime(jobInfoEntity.getTriggerLastTime());
        updateJobInfoEntity.setTriggerNextTime(jobInfoEntity.getTriggerNextTime());
        jobInfoDomainService.update(updateJobInfoEntity);
    }

    public void trigger(int jobId, String triggerType, int failRetryCount, String executorShardingParam, String executorParam, String addressList) {
        ThreadPoolExecutor threadPoolExecutor = JOB_TASK_FAST;

        AtomicInteger jobTimeoutCount = jobTimeoutCountMap.get(jobId);
        if (Objects.nonNull(jobTimeoutCount) && jobTimeoutCount.get() > 10) {
            threadPoolExecutor = JOB_TASK_SLOW;
        }

        threadPoolExecutor.execute(() -> {
            long start = System.currentTimeMillis();

            try {
                triggerRun(jobId, triggerType, failRetryCount, executorShardingParam, executorParam, addressList);
            } catch (Exception e) {
                log.error(MessageUtil.format("trigger error", e, "jobId", jobId));
            } finally {
                long timeWindowNow = System.currentTimeMillis() / 60_000;
                if (!Objects.equals(timeWindowNow, timeWindow)) {
                    synchronized (this) {
                        if (!Objects.equals(timeWindowNow, timeWindow)) {
                            timeWindow = timeWindowNow;
                            jobTimeoutCountMap.clear();
                        }
                    }
                }

                long cost = System.currentTimeMillis() - start;
                if (cost > 500) {
                    AtomicInteger timeoutCount = jobTimeoutCountMap.putIfAbsent(jobId, new AtomicInteger(1));
                    if (Objects.nonNull(timeoutCount)) {
                        timeoutCount.incrementAndGet();
                    }
                }
            }
        });
    }

    private void triggerRun(int jobId, String triggerType, int failRetryCount, String executorShardingParam, String executorParam, String addressList) {
    }
}
