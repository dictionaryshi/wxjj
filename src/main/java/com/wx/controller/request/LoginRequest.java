package com.wx.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * LoginRequest
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/9.
 */
@ApiModel("登录参数")
@Getter
@Setter
@ToString
public class LoginRequest {

    @NotBlank(message = "登录账号不能为空")
    @ApiModelProperty(value = "登录账号", required = true)
    private String passport;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码", required = true)
    private String captcha;

    @NotBlank(message = "验证码唯一标识不能为空")
    @ApiModelProperty(value = "验证码唯一标识", required = true)
    private String captchaId;
}
