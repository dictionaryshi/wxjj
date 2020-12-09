package com.wx.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * GetLoginUserRequest
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/9.
 */
@ApiModel("获取当前登陆用户信息")
@Getter
@Setter
@ToString
public class GetLoginUserRequest {

    @NotBlank(message = "sso token不为空")
    @ApiModelProperty(value = "sso token", required = true)
    private String token;
}
