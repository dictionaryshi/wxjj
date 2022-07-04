package com.wx.domain.job.service;

import com.wx.dao.warehouse.mapper.JobInfoDOMapper;
import com.wx.dao.warehouse.model.JobInfoDO;
import com.wx.dao.warehouse.model.JobInfoDOExample;
import com.wx.domain.job.entity.JobInfoEntity;
import com.wx.domain.job.factory.JobInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : shichunyang
 * Date    : 2022/7/4
 * Time    : 4:28 下午
 * ---------------------------------------
 * Desc    : JobInfoDomainService
 */
@Service
public class JobInfoDomainService {

    @Autowired
    private JobInfoDOMapper jobInfoMapper;

    @Autowired
    private JobInfoFactory jobInfoFactory;

    public List<JobInfoEntity> queryscheduleJobs(long maxNextTime, int limit) {
        JobInfoDOExample jobInfoDOExample = new JobInfoDOExample();
        jobInfoDOExample.setOrderByClause("trigger_next_time asc");
        jobInfoDOExample.setOffset(0);
        jobInfoDOExample.setLimit(limit);

        JobInfoDOExample.Criteria criteria = jobInfoDOExample.createCriteria();
        criteria.andTriggerStatusEqualTo(1);
        criteria.andTriggerNextTimeLessThanOrEqualTo(maxNextTime);
        List<JobInfoDO> jobInfos = jobInfoMapper.selectByExample(jobInfoDOExample);
        return jobInfoFactory.toJobInfoEntity(jobInfos);
    }

    public void update(JobInfoEntity updateJobInfoEntity) {
        JobInfoDO jobInfoDO = jobInfoFactory.toJobInfoDO(updateJobInfoEntity);
        jobInfoMapper.updateByPrimaryKeySelective(jobInfoDO);
    }
}
