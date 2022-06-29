package com.wx.domain.job.factory;

import com.scy.core.spring.ApplicationContextUtil;
import com.wx.dao.warehouse.model.JobRegistryDO;
import com.wx.domain.job.entity.JobRegistryEntity;
import org.mapstruct.Mapper;

/**
 * @author : shichunyang
 * Date    : 2022/6/29
 * Time    : 1:50 下午
 * ---------------------------------------
 * Desc    : JobRegistryFactory
 */
@Mapper(componentModel = ApplicationContextUtil.SPRING)
public interface JobRegistryFactory {

    JobRegistryDO toJobRegistryDO(JobRegistryEntity jobRegistryEntity);
}
