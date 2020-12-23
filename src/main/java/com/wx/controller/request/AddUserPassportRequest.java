package com.wx.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author : shichunyang
 * Date    : 2020/12/23
 * Time    : 11:00 上午
 * ---------------------------------------
 * Desc    : 添加用户账号
 */
@ApiModel("添加用户账号请求对象")
@Getter
@Setter
@ToString
public class AddUserPassportRequest {

    @NotBlank(message = "登录账号不能为空")
    @ApiModelProperty(value = "登录账号", required = true)
    private String passport;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
