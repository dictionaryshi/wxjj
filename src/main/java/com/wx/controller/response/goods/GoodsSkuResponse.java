package com.wx.controller.response.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2020/12/25
 * Time    : 9:31 下午
 * ---------------------------------------
 * Desc    : 商品响应对象
 */
@ApiModel("商品响应对象")
@Getter
@Setter
@ToString
public class GoodsSkuResponse {

    @ApiModelProperty(value = "商品id", required = true, example = "123456")
    private Long skuId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", required = true, example = "电视柜")
    private String skuName;

    /**
     * 品类id
     */
    @ApiModelProperty(value = "品类id", required = true, example = "123456")
    private Long categoryId;

    /**
     * 品类名称
     */
    @ApiModelProperty(value = "品类名称", required = true, example = "桌子")
    private String categoryName;
}
