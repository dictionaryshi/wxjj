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
 * Time    : 10:22 下午
 * ---------------------------------------
 * Desc    : 分页查询商品库存请求
 */
@ApiModel("分页查询商品库存请求")
@Getter
@Setter
@ToString
public class QuerySkuStockByPageRequest {

    /**
     * 仓库id
     */
    @NotNull(message = "仓库id 不为null")
    @ApiModelProperty(value = "仓库id", required = true, example = "123456")
    private Long stockBaseInfoId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", required = false, example = "桌子")
    private String skuName;
}
