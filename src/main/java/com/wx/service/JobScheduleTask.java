package com.wx.service;

import com.scy.core.enums.JvmStatus;
import com.scy.core.format.DateUtil;
import com.scy.core.thread.ThreadPoolUtil;
import com.scy.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Optional;
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

    @Override
    public void afterPropertiesSet() throws Exception {
        THREAD_POOL_EXECUTOR.execute(() -> {
            ThreadUtil.quietSleep(PRE_READ_TIME - System.currentTimeMillis() % DateUtil.SECOND);

            log.info("JobScheduleTask success");

            while (!JvmStatus.JVM_CLOSE_FLAG) {
                boolean readSuccess = Boolean.TRUE;
                long start = System.currentTimeMillis();
                long cost = System.currentTimeMillis() - start;
                if (cost < 1000) {
                    ThreadUtil.quietSleep((readSuccess ? DateUtil.SECOND : PRE_READ_TIME) - System.currentTimeMillis() % DateUtil.SECOND);
                }
            }

            log.info("JobScheduleTask stop");

            Optional.ofNullable(scheduleThread).ifPresent(Thread::interrupt);
        });

        THREAD_POOL_EXECUTOR.execute(() -> {
            scheduleThread = Thread.currentThread();
            while (!JvmStatus.JVM_CLOSE_FLAG) {
            }
        });
    }
}
