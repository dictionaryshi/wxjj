package com.wx.controller.api;

import com.scy.core.rest.ResponseResult;
import com.scy.web.annotation.SignCheck;
import com.wx.controller.request.job.RegistryRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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

    @ApiOperation("注册应用")
    @SignCheck
    @PostMapping(value = "/registry")
    public ResponseResult<?> registry(@Valid RegistryRequest registryRequest) {
        return ResponseResult.success(null);
    }
}
