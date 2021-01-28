package com.wx.controller.request.stock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author : shichunyang
 * Date    : 2021/1/28
 * Time    : 11:10 上午
 * ---------------------------------------
 * Desc    : 添加仓库基本信息请求
 */
@ApiModel("添加仓库基本信息请求")
@Getter
@Setter
@ToString
public class AddStockBaseInfoRequest {

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @ApiModelProperty(value = "地址")
    private String address;
}
