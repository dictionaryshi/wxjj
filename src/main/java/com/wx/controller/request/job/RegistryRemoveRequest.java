package com.wx.controller.request.job;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author : shichunyang
 * Date    : 2022/6/29
 * Time    : 5:48 下午
 * ---------------------------------------
 * Desc    : RegistryRemoveRequest
 */
@ApiModel("删除注册请求")
@Getter
@Setter
@ToString
public class RegistryRemoveRequest {

    /**
     * app标识
     */
    @NotBlank(message = "app标识 不为空")
    @ApiModelProperty(value = "app标识", required = true)
    private String appName;

    /**
     * 地址
     */
    @NotBlank(message = "地址不为空")
    @ApiModelProperty(value = "地址", required = true)
    private String address;
}
