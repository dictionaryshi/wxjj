package com.wx.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scy.core.CollectionUtil;
import com.scy.core.Route;
import com.scy.core.StringUtil;
import com.scy.core.format.DateUtil;
import com.scy.core.format.MessageUtil;
import com.scy.core.json.JsonUtil;
import com.scy.core.net.HttpOptions;
import com.scy.core.net.HttpUtil;
import com.scy.core.rest.ResponseResult;
import com.scy.core.thread.ThreadPoolUtil;
import com.scy.netty.job.ExecutorBlockStrategyEnum;
import com.scy.netty.job.JobParam;
import com.scy.netty.job.JobTypeEnum;
import com.scy.netty.job.annotation.Job;
import com.wx.dao.warehouse.model.JobGroupDO;
import com.wx.dao.warehouse.model.JobLogDO;
import com.wx.domain.job.entity.JobInfoEntity;
import com.wx.domain.job.entity.JobRegistryEntity;
import com.wx.domain.job.service.JobInfoDomainService;
import com.wx.domain.job.service.JobRegistryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
        JobInfoEntity jobInfoEntity = jobInfoDomainService.getJobInfoEntityById(jobId);
        if (Objects.isNull(jobInfoEntity)) {
            return;
        }

        if (Objects.nonNull(executorParam)) {
            jobInfoEntity.setExecutorParam(executorParam);
        }

        JobGroupDO jobGroupDO = jobInfoDomainService.getJobGroupById(jobInfoEntity.getJobGroupId());
        if (Objects.isNull(jobGroupDO)) {
            return;
        }

        if (!StringUtil.isEmpty(addressList)) {
            jobGroupDO.setAddressType(1);
            jobGroupDO.setAddressList(addressList.trim());
        }

        int[] shardingParam = null;
        if (Objects.nonNull(executorShardingParam)) {
            String[] shardingArr = executorShardingParam.split("/");
            if (shardingArr.length == 2 && StringUtil.isNumber(shardingArr[0]) && StringUtil.isNumber(shardingArr[1])) {
                shardingParam = new int[2];
                shardingParam[0] = Integer.parseInt(shardingArr[0]);
                shardingParam[1] = Integer.parseInt(shardingArr[1]);
            }
        }

        int finalFailRetryCount = failRetryCount >= 0 ? failRetryCount : jobInfoEntity.getExecutorFailRetryCount();

        List<String> addresses = JsonUtil.json2Object(jobGroupDO.getAddressList(), new TypeReference<List<String>>() {
        });
        if (Objects.equals("broadcast", jobInfoEntity.getExecutorRouteStrategy()) && !CollectionUtil.isEmpty(addresses) && Objects.isNull(shardingParam)) {
            for (int i = 0; i < addresses.size(); i++) {
                processTrigger(jobGroupDO, jobInfoEntity, finalFailRetryCount, triggerType, i, addresses.size());
            }
        } else {
            if (Objects.isNull(shardingParam)) {
                shardingParam = new int[]{0, 1};
            }
            processTrigger(jobGroupDO, jobInfoEntity, finalFailRetryCount, triggerType, shardingParam[0], shardingParam[1]);
        }
    }

    private void processTrigger(JobGroupDO jobGroupDO, JobInfoEntity jobInfoEntity, int finalFailRetryCount, String triggerType, int index, int total) {
        JobLogDO jobLogDO = new JobLogDO();
        jobLogDO.setJobGroupId(jobGroupDO.getId());
        jobLogDO.setJobId(jobInfoEntity.getId());
        jobLogDO.setExecutorApp(jobGroupDO.getAppName());
        jobLogDO.setExecutorHandler(jobGroupDO.getName());
        jobLogDO.setExecutorParam(jobInfoEntity.getExecutorParam());
        jobLogDO.setExecutorShardingParam(Objects.equals("broadcast", jobInfoEntity.getExecutorRouteStrategy())
                ? String.valueOf(index).concat("/").concat(String.valueOf(total)) : null);
        jobLogDO.setExecutorFailRetryCount(finalFailRetryCount);
        jobLogDO.setTriggerTime(DateUtil.getCurrentDate().getTime());
        jobLogDO.setAlarmStatus(-1);

        jobInfoDomainService.insertJobLog(jobLogDO);

        JobParam jobParam = new JobParam();
        jobParam.setJobId(jobInfoEntity.getId().intValue());
        jobParam.setJobType(JobTypeEnum.BEAN.getType());
        jobParam.setExecutorHandler(jobGroupDO.getName());
        jobParam.setExecutorParams(jobInfoEntity.getExecutorParam());
        jobParam.setExecutorBlockStrategy(ExecutorBlockStrategyEnum.SERIAL_EXECUTION.getType());
        jobParam.setExecutorTimeout(jobInfoEntity.getExecutorTimeout());
        jobParam.setLogId(jobLogDO.getId());
        jobParam.setLogDateTime(jobLogDO.getTriggerTime());
        jobParam.setBroadcastIndex(index);
        jobParam.setBroadcastTotal(total);

        String address = null;
        List<String> addresses = JsonUtil.json2Object(jobGroupDO.getAddressList(), new TypeReference<List<String>>() {
        });
        if (!CollectionUtil.isEmpty(addresses)) {
            if (Objects.equals("broadcast", jobInfoEntity.getExecutorRouteStrategy())) {
                if (index < addresses.size()) {
                    address = addresses.get(index);
                } else {
                    address = addresses.get(0);
                }
            } else {
                address = Route.busyOrFailSkip(new TreeSet<>(addresses), addr -> {
                    ResponseResult<Boolean> responseResult = HttpUtil.post(addr + "/beat", JsonUtil.convertValue(jobParam, new TypeReference<Map<String, Object>>() {
                    }), new TypeReference<ResponseResult<Boolean>>() {
                    }, HttpOptions.build().contentType(HttpUtil.APPLICATION_JSON_VALUE));
                    return Optional.ofNullable(responseResult).map(ResponseResult::getData).orElse(Boolean.FALSE);
                });
            }
        }
    }
}
