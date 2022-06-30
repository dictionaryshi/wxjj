package com.wx.service;

import com.scy.netty.job.annotation.Job;
import com.wx.domain.job.entity.JobRegistryEntity;
import com.wx.domain.job.service.JobRegistryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private JobRegistryDomainService jobRegistryDomainService;

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
}
