package com.wx.service;

import com.scy.core.CollectionUtil;
import com.scy.core.CronUtil;
import com.scy.core.enums.JvmStatus;
import com.scy.core.format.DateUtil;
import com.scy.core.format.MessageUtil;
import com.scy.core.spring.ApplicationContextUtil;
import com.scy.core.thread.Delay;
import com.scy.core.thread.ThreadPoolUtil;
import com.scy.core.thread.ThreadUtil;
import com.scy.core.trace.TraceUtil;
import com.scy.db.constant.DbConstant;
import com.wx.domain.job.entity.JobInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : shichunyang
 * Date    : 2022/7/1
 * Time    : 4:35 下午
 * ---------------------------------------
 * Desc    : JobScheduleTask
 */
@Slf4j
@Component
public class JobScheduleTask implements InitializingBean {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = ThreadPoolUtil.getThreadPool("job-task", 5, 10, 5);

    public static final long PRE_READ_TIME = 5000;

    private Thread scheduleThread;

    private final DelayQueue<Delay<Long>> delayQueue = new DelayQueue<>();

    @Autowired
    private JobFacade jobFacade;

    @Override
    public void afterPropertiesSet() throws Exception {
        THREAD_POOL_EXECUTOR.execute(() -> {
            ThreadUtil.quietSleep(PRE_READ_TIME - System.currentTimeMillis() % DateUtil.SECOND);

            log.info("JobScheduleTask success");

            while (!JvmStatus.JVM_CLOSE_FLAG) {
                boolean readSuccess = Boolean.TRUE;
                long start = System.currentTimeMillis();

                Connection connection = null;
                Boolean autoCommit = null;
                PreparedStatement preparedStatement = null;
                try {
                    TraceUtil.setTraceId(null);

                    connection = ApplicationContextUtil.getBean("warehouse" + DbConstant.DATA_SOURCE_MASTER, DataSource.class).getConnection();
                    autoCommit = connection.getAutoCommit();
                    connection.setAutoCommit(Boolean.FALSE);

                    preparedStatement = connection.prepareStatement("select * from job_lock where lock_name = 'schedule_lock' for update");
                    preparedStatement.execute();

                    long maxNextTime = System.currentTimeMillis() + PRE_READ_TIME;

                    List<JobInfoEntity> jobInfoEntities = jobFacade.queryscheduleJobs(maxNextTime, 4000);
                    if (CollectionUtil.isEmpty(jobInfoEntities)) {
                        readSuccess = Boolean.FALSE;
                    }

                    jobInfoEntities.forEach(this::addQueue);

                    jobInfoEntities.forEach(jobFacade::updateNextTime);
                } catch (Exception e) {
                    log.error(MessageUtil.format("JobScheduleTask error", e));
                } finally {
                    if (Objects.nonNull(connection)) {
                        try {
                            connection.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (Objects.nonNull(autoCommit)) {
                            try {
                                connection.setAutoCommit(autoCommit);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            connection.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (Objects.nonNull(preparedStatement)) {
                        try {
                            preparedStatement.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    TraceUtil.clearTrace();
                }

                long cost = System.currentTimeMillis() - start;
                if (cost < 1000) {
                    ThreadUtil.quietSleep((readSuccess ? DateUtil.SECOND : PRE_READ_TIME) - System.currentTimeMillis() % DateUtil.SECOND);
                }
            }

            log.info("JobScheduleTask stop");

            while (delayQueue.size() > 0) {
                ThreadUtil.quietSleep(PRE_READ_TIME);
            }

            Optional.ofNullable(scheduleThread).ifPresent(Thread::interrupt);
        });

        THREAD_POOL_EXECUTOR.execute(() -> {
            scheduleThread = Thread.currentThread();
            while (!JvmStatus.JVM_CLOSE_FLAG) {
                try {
                    TraceUtil.setTraceId(null);

                    Delay<Long> delay = delayQueue.take();
                    List<Delay<Long>> delayList = new ArrayList<>();
                    int count = delayQueue.drainTo(delayList, 3999);
                    delayList.add(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    TraceUtil.clearTrace();
                }
            }
        });
    }

    private void addQueue(JobInfoEntity jobInfoEntity) {
        long nowTime = System.currentTimeMillis();
        if (jobInfoEntity.getTriggerNextTime() < (nowTime - PRE_READ_TIME)) {
            jobInfoEntity.setTriggerNextTime(nowTime);
        }

        if (jobInfoEntity.getTriggerNextTime() >= nowTime) {
            delayQueue.add(new Delay<>(jobInfoEntity.getTriggerNextTime(), jobInfoEntity.getId()));
        }

        freshNextTime(jobInfoEntity, new Date(jobInfoEntity.getTriggerNextTime()));

        if (Objects.equals(jobInfoEntity.getTriggerStatus(), 1) && jobInfoEntity.getTriggerNextTime() <= (nowTime + PRE_READ_TIME)) {
            addQueue(jobInfoEntity);
        }
    }

    private void freshNextTime(JobInfoEntity jobInfoEntity, Date fromTime) {
        jobInfoEntity.setTriggerLastTime(jobInfoEntity.getTriggerNextTime());

        String scheduleType = jobInfoEntity.getScheduleType();
        if (Objects.equals(scheduleType, "cron")) {
            LocalDateTime localDateTime = CronUtil.nextExecuteTime(jobInfoEntity.getScheduleConfig(), DateUtil.date2LocalDateTime(fromTime));
            if (Objects.isNull(localDateTime)) {
                jobInfoEntity.setTriggerStatus(0);
                jobInfoEntity.setTriggerNextTime(0L);
                return;
            }

            long nextTime = DateUtil.toEpochMilli(localDateTime);
            jobInfoEntity.setTriggerNextTime(nextTime);
            return;
        }

        jobInfoEntity.setTriggerNextTime(new Date(fromTime.getTime() + (Integer.parseInt(jobInfoEntity.getScheduleConfig()) * DateUtil.SECOND)).getTime());
    }
}
