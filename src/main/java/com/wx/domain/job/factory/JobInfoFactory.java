package com.wx.domain.job.factory;

import com.scy.core.spring.ApplicationContextUtil;
import com.wx.dao.warehouse.model.JobInfoDO;
import com.wx.domain.job.entity.JobInfoEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author : shichunyang
 * Date    : 2022/7/4
 * Time    : 4:32 下午
 * ---------------------------------------
 * Desc    : JobInfoFactory
 */
@Mapper(componentModel = ApplicationContextUtil.SPRING)
public interface JobInfoFactory {

    List<JobInfoEntity> toJobInfoEntity(List<JobInfoDO> jobInfos);

    JobInfoEntity toJobInfoEntity(JobInfoDO jobInfoDO);
}
