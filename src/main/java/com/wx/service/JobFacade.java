package com.wx.service;

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
}
