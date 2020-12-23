package com.wx.controller.api;

import com.scy.core.CollectionUtil;
import com.scy.core.rest.ResponseResult;
import com.scy.web.annotation.LoginCheck;
import com.scy.web.model.UserTokenBO;
import com.scy.web.util.CookieUtil;
import com.scy.web.util.LoginUtil;
import com.wx.controller.assembler.CaptchaAssembler;
import com.wx.controller.assembler.LoginAssembler;
import com.wx.controller.request.AddUserPassportRequest;
import com.wx.controller.request.GetLoginUserRequest;
import com.wx.controller.request.LoginRequest;
import com.wx.controller.request.UpdateUserPassportRequest;
import com.wx.controller.response.CaptchaResponse;
import com.wx.controller.response.UserPassportResponse;
import com.wx.domain.code.entity.CaptchaEntity;
import com.wx.domain.passport.entity.UserPassportEntity;
import com.wx.service.SsoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @ApiOperation("查询当前登录用户信息")
    @GetMapping("/get-login-user")
    public ResponseResult<UserTokenBO> getLoginUser(
            @Valid GetLoginUserRequest getLoginUserRequest
    ) {
        UserPassportEntity userPassportEntity = ssoService.getLoginUser(getLoginUserRequest.getToken());
        UserTokenBO userTokenBO = LoginAssembler.toUserTokenBO(userPassportEntity);
        return ResponseResult.success(userTokenBO);
    }

    @ApiOperation("查询所有用户账号信息")
    @LoginCheck
    @GetMapping("/list-all-user-passports")
    public ResponseResult<List<UserPassportResponse>> listAllUserPassports(
    ) {
        List<UserPassportEntity> userPassportEntities = ssoService.listAllUserPassports();
        List<UserPassportResponse> userPassportResponses = CollectionUtil.map(userPassportEntities, LoginAssembler::toUserPassportResponse).collect(Collectors.toList());
        return ResponseResult.success(userPassportResponses);
    }

    @ApiOperation("添加用户账号信息")
    @LoginCheck
    @PostMapping("/add-user-passport")
    public ResponseResult<Long> addUserPassport(
            @RequestBody @Valid AddUserPassportRequest addUserPassportRequest
    ) {
        long userId = ssoService.insertUserPassport(addUserPassportRequest.getPassport(), addUserPassportRequest.getPassword());
        return ResponseResult.success(userId);
    }

    @ApiOperation("修改用户账号信息")
    @LoginCheck
    @PostMapping("/update-user-passport")
    public ResponseResult<Long> updateUserPassport(
            @RequestBody @Valid UpdateUserPassportRequest updateUserPassportRequest
    ) {
        long userId = ssoService.updateUserPassport(updateUserPassportRequest.getPassport(), updateUserPassportRequest.getPassword());
        return ResponseResult.success(userId);
    }
}
