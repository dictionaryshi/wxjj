package com.wx.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2020/12/24
 * Time    : 10:05 上午
 * ---------------------------------------
 * Desc    : 查询用户账号
 */
@ApiModel("查询用户账号请求对象")
@Getter
@Setter
@ToString
public class GetUserPassportRequest {

    /**
     * 用户id
     */
    @NotNull(message = "userId 不为null")
    @ApiModelProperty(value = "用户唯一标识", required = true, example = "123456")
    private Long userId;
}
