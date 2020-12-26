package com.wx.controller.request.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2020/12/26
 * Time    : 10:48 上午
 * ---------------------------------------
 * Desc    : 分页查询sku请求对象
 */
@ApiModel("分页查询sku请求对象")
@Getter
@Setter
@ToString
public class QueryGoodsSkuByPageRequest {

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", required = false, example = "电视柜")
    private String skuName;

    /**
     * 品类id
     */
    @ApiModelProperty(value = "品类id", required = false, example = "123456")
    private Long categoryId;
}
