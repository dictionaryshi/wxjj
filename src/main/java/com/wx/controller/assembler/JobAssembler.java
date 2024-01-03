package com.wx.controller.assembler;

import com.scy.core.StringUtil;
import com.scy.core.format.DateUtil;
import com.scy.core.spring.ApplicationContextUtil;
import com.wx.controller.request.job.RegistryRemoveRequest;
import com.wx.controller.request.job.RegistryRequest;
import com.wx.domain.job.entity.JobRegistryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author : shichunyang
 * Date    : 2022/6/29
 * Time    : 3:01 下午
 * ---------------------------------------
 * Desc    : JobAssembler
 */
@Mapper(componentModel = ApplicationContextUtil.SPRING, imports = {DateUtil.class, StringUtil.class})
public interface JobAssembler {

    JobAssembler CONVERTOR = Mappers.getMapper(JobAssembler.class);

    JobRegistryEntity toJobRegistryEntity(RegistryRequest registryRequest);

    JobRegistryEntity toJobRegistryEntity(RegistryRemoveRequest registryRemoveRequest);
}
