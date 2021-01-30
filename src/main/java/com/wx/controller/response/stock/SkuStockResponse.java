package com.wx.controller.response.stock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2021/1/30
 * Time    : 10:43 下午
 * ---------------------------------------
 * Desc    : 商品库存响应
 */
@ApiModel("商品库存响应")
@Getter
@Setter
@ToString
public class SkuStockResponse {

    /**
     * 仓库id
     */
    @ApiModelProperty(value = "仓库id", required = true, example = "123456")
    private Long stockBaseInfoId;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id", required = true, example = "123456")
    private Long skuId;

    /**
     * 库存
     */
    @ApiModelProperty(value = "库存", required = true, example = "30")
    private Long stock;

    /**
     * 仓库名称
     */
    @ApiModelProperty(value = "仓库名称", required = false, example = "河南仓库")
    private String stockName;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", required = false, example = "桌子")
    private String skuName;
}
