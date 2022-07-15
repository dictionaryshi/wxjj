package com.wx.domain.job.service;

import com.wx.dao.warehouse.mapper.JobGroupDOMapper;
import com.wx.dao.warehouse.mapper.JobInfoDOMapper;
import com.wx.dao.warehouse.mapper.JobLogDOMapper;
import com.wx.dao.warehouse.model.JobGroupDO;
import com.wx.dao.warehouse.model.JobInfoDO;
import com.wx.dao.warehouse.model.JobInfoDOExample;
import com.wx.dao.warehouse.model.JobLogDO;
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
    private JobGroupDOMapper jobGroupMapper;

    @Autowired
    private JobLogDOMapper jobLogMapper;

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

    public JobInfoEntity getJobInfoEntityById(long id) {
        JobInfoDO jobInfoDO = jobInfoMapper.selectByPrimaryKey(id);
        return jobInfoFactory.toJobInfoEntity(jobInfoDO);
    }

    public JobGroupDO getJobGroupById(long id) {
        return jobGroupMapper.selectByPrimaryKey(id);
    }

    public void insertJobLog(JobLogDO jobLogDO) {
        jobLogMapper.insertSelective(jobLogDO);
    }

    public void updateJobLog(JobLogDO jobLogDO) {
        jobLogMapper.updateByPrimaryKeySelective(jobLogDO);
    }

    public JobLogDO getJobLogById(long id) {
        return jobLogMapper.selectByPrimaryKey(id);
    }
}
