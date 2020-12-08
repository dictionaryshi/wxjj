package com.wx.controller.api;

import com.scy.core.rest.ResponseResult;
import com.wx.controller.assembler.CaptchaAssembler;
import com.wx.controller.response.CaptchaResponse;
import com.wx.domain.code.entity.CaptchaEntity;
import com.wx.service.SsoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SsoController
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
@Api(tags = "SSO API")
@Slf4j
@RequestMapping("/sso")
@RestController
public class SsoController {

    @Autowired
    private SsoService ssoService;

    @ApiOperation("获取图片验证码")
    @GetMapping("/get-captcha")
    public ResponseResult<CaptchaResponse> getCaptcha() {
        CaptchaEntity captchaEntity = ssoService.getCaptcha();
        return ResponseResult.success(CaptchaAssembler.toCaptchaResponse(captchaEntity));
    }
}
