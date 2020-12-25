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
 * Time    : 11:39 上午
 * ---------------------------------------
 * Desc    : 添加商品请求对象
 */
@ApiModel("添加商品请求对象")
@Getter
@Setter
@ToString
public class AddGoodsSkuRequest {

    /**
     * 商品名称
     */
    @NotBlank(message = "skuName 不为空")
    @ApiModelProperty(value = "商品名称", required = true, example = "电视柜")
    private String skuName;

    /**
     * 品类id
     */
    @NotNull(message = "categoryId 不为null")
    @ApiModelProperty(value = "品类id", required = true, example = "123456")
    private Long categoryId;
}
