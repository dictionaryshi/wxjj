package com.wx.domain.job.service;

import com.scy.core.format.DateUtil;
import com.scy.core.thread.ThreadPoolUtil;
import com.wx.dao.warehouse.mapper.JobRegistryDOMapper;
import com.wx.dao.warehouse.model.JobRegistryDO;
import com.wx.dao.warehouse.model.JobRegistryDOExample;
import com.wx.domain.job.entity.JobRegistryEntity;
import com.wx.domain.job.factory.JobRegistryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : shichunyang
 * Date    : 2022/6/29
 * Time    : 1:34 下午
 * ---------------------------------------
 * Desc    : JobRegistryDomainService
 */
@Service
public class JobRegistryDomainService {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = ThreadPoolUtil.getThreadPool("job-registry", 1, 5, 5);

    @Autowired
    private JobRegistryDOMapper jobRegistryMapper;

    @Autowired
    private JobRegistryFactory jobRegistryFactory;

    public void registry(JobRegistryEntity jobRegistryEntity) {
        THREAD_POOL_EXECUTOR.execute(() -> {
            JobRegistryDO jobRegistryDO = jobRegistryFactory.toJobRegistryDO(jobRegistryEntity);
            jobRegistryDO.setUpdatedAt(DateUtil.getCurrentDate());

            JobRegistryDOExample jobRegistryDOExample = new JobRegistryDOExample();
            JobRegistryDOExample.Criteria criteria = jobRegistryDOExample.createCriteria();
            criteria.andAppNameEqualTo(jobRegistryEntity.getAppName());
            criteria.andAddressEqualTo(jobRegistryEntity.getAddress());

            int count = jobRegistryMapper.updateByExampleSelective(jobRegistryDO, jobRegistryDOExample);
            if (count > 0) {
                return;
            }

            jobRegistryMapper.insertSelective(jobRegistryDO);
        });
    }
}
