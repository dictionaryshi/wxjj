package com.wx.controller.request.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2020/12/26
 * Time    : 10:17 上午
 * ---------------------------------------
 * Desc    : 根据分类查询所有商品请求对象
 */
@ApiModel("根据分类查询所有商品请求对象")
@Getter
@Setter
@ToString
public class QueryGoodsSkuByCategoryRequest {

    @NotNull(message = "categoryId 不为null")
    @ApiModelProperty(value = "品类id", required = true, example = "123456")
    private Long categoryId;
}
