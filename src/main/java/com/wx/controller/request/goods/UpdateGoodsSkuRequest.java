package com.wx.controller.request.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2020/12/25
 * Time    : 1:34 下午
 * ---------------------------------------
 * Desc    : 修改商品请求对象
 */
@ApiModel("修改商品请求对象")
@Getter
@Setter
@ToString
public class UpdateGoodsSkuRequest {

    @NotNull(message = "skuId 不为null")
    @ApiModelProperty(value = "商品id", required = true, example = "123456")
    private Long skuId;

    @NotBlank(message = "skuName 不为空")
    @ApiModelProperty(value = "商品名称", required = true, example = "电视柜")
    private String skuName;

    @NotNull(message = "categoryId 不为null")
    @ApiModelProperty(value = "分类id", required = true, example = "123456")
    private Long categoryId;
}
