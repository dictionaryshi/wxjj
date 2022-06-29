package com.wx.controller.api;

import com.scy.core.rest.ResponseResult;
import com.wx.controller.assembler.JobAssembler;
import com.wx.controller.request.job.RegistryRemoveRequest;
import com.wx.controller.request.job.RegistryRequest;
import com.wx.service.JobFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author : shichunyang
 * Date    : 2022/6/29
 * Time    : 1:04 下午
 * ---------------------------------------
 * Desc    : JobController
 */
@Slf4j
@Api(tags = "jobAPI")
@RequestMapping("/job")
@RestController
public class JobController {

    @Autowired
    private JobFacade jobFacade;

    @Autowired
    private JobAssembler jobAssembler;

    @ApiOperation("注册应用")
    @PostMapping(value = "/registry")
    public ResponseResult<?> registry(@Valid RegistryRequest registryRequest) {
        jobFacade.registry(jobAssembler.toJobRegistryEntity(registryRequest));
        return ResponseResult.success(null);
    }

    @ApiOperation("删除注册")
    @PostMapping(value = "/registryRemove")
    public ResponseResult<?> registryRemove(@Valid RegistryRemoveRequest registryRemoveRequest) {
        jobFacade.registryRemove(jobAssembler.toJobRegistryEntity(registryRemoveRequest));
        return ResponseResult.success(null);
    }
}
