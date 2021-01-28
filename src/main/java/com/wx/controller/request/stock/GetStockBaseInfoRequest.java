package com.wx.controller.request.stock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2021/1/28
 * Time    : 1:20 下午
 * ---------------------------------------
 * Desc    : 查询仓库基本信息请求
 */
@ApiModel(value = "查询仓库基本信息请求")
@Getter
@Setter
@ToString
public class GetStockBaseInfoRequest {

    @NotNull(message = "id 不为null")
    @ApiModelProperty(value = "id", required = true, example = "123456")
    private Long id;
}
