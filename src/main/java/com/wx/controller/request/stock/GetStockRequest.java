package com.wx.controller.request.stock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2021/10/24
 * Time    : 3:17 下午
 * ---------------------------------------
 * Desc    : 查询库存
 */
@ApiModel("查询库存")
@Getter
@Setter
@ToString
public class GetStockRequest {

    /**
     * 仓库id
     */
    @NotNull(message = "仓库id 不能为null")
    @ApiModelProperty(value = "仓库id", required = true, example = "123456")
    private Long stockBaseInfoId;

    /**
     * 商品id
     */
    @NotNull(message = "商品id 不能为null")
    @ApiModelProperty(value = "商品id", required = true, example = "123456")
    private Long skuId;
}
