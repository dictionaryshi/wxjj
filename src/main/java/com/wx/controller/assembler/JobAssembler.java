package com.wx.controller.assembler;

import com.scy.core.spring.ApplicationContextUtil;
import com.wx.controller.request.job.RegistryRequest;
import com.wx.domain.job.entity.JobRegistryEntity;
import org.mapstruct.Mapper;

/**
 * @author : shichunyang
 * Date    : 2022/6/29
 * Time    : 3:01 下午
 * ---------------------------------------
 * Desc    : JobAssembler
 */
@Mapper(componentModel = ApplicationContextUtil.SPRING)
public interface JobAssembler {

    JobRegistryEntity toJobRegistryEntity(RegistryRequest registryRequest);
}
