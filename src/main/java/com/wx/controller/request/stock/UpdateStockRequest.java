package com.wx.controller.request.stock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2021/1/30
 * Time    : 9:17 下午
 * ---------------------------------------
 * Desc    : 盘点库存
 */
@ApiModel("盘点库存")
@Getter
@Setter
@ToString
public class UpdateStockRequest {

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

    /**
     * 库存
     */
    @NotNull(message = "库存 不能为null")
    @ApiModelProperty(value = "库存", required = true, example = "300")
    private Long stock;
}
