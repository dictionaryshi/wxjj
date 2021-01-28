package com.wx.controller.response.stock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2021/1/28
 * Time    : 1:15 下午
 * ---------------------------------------
 * Desc    : 仓库基本信息响应
 */
@ApiModel(value = "仓库基本信息响应")
@Getter
@Setter
@ToString
public class StockBaseInfoResponse {

    @ApiModelProperty(value = "id", required = true, example = "123456")
    private Long id;

    @ApiModelProperty(value = "名称", required = true, example = "河南仓库")
    private String name;

    @ApiModelProperty(value = "地址", required = true, example = "河南省洛阳市")
    private String address;
}
