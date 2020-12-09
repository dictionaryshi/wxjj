package com.wx.controller.api;

import com.scy.core.rest.ResponseResult;
import com.scy.web.model.UserTokenBO;
import com.scy.web.util.CookieUtil;
import com.scy.web.util.LoginUtil;
import com.wx.controller.assembler.CaptchaAssembler;
import com.wx.controller.assembler.LoginAssembler;
import com.wx.controller.request.LoginRequest;
import com.wx.controller.response.CaptchaResponse;
import com.wx.domain.code.entity.CaptchaEntity;
import com.wx.domain.passport.entity.UserPassportEntity;
import com.wx.service.SsoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

    /**
     * 登录
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseResult<UserTokenBO> login(
            @RequestBody @Valid LoginRequest loginRequest,
            HttpServletResponse response
    ) {
        UserPassportEntity userPassportEntity = LoginAssembler.toUserPassportEntity(loginRequest);
        userPassportEntity = ssoService.login(userPassportEntity, loginRequest.getCaptchaId(), loginRequest.getCaptcha());

        String domain = "scy.com";
        CookieUtil.sendCookie(response, LoginUtil.COOKIE_SSO, userPassportEntity.getToken(), domain);

        UserTokenBO userTokenBO = LoginAssembler.toUserTokenBO(userPassportEntity);
        return ResponseResult.success(userTokenBO);
    }
}
