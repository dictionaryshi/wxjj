package com.wx.controller.request.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2020/12/25
 * Time    : 9:29 下午
 * ---------------------------------------
 * Desc    : 查询商品请求对象
 */
@ApiModel("查询商品请求对象")
@Getter
@Setter
@ToString
public class GetGoodsSkuRequest {

    @NotNull(message = "skuId 不为null")
    @ApiModelProperty(value = "商品id", required = true, example = "123456")
    private Long skuId;
}
