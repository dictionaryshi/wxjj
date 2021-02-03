package com.wx.controller.request.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2021/2/3
 * Time    : 8:55 下午
 * ---------------------------------------
 * Desc    : 删除订单条目请求
 */
@ApiModel("删除订单条目请求")
@Getter
@Setter
@ToString
public class DeleteOrderItemRequest {

    /**
     * 订单id
     */
    @NotNull(message = "orderId 不为null")
    @ApiModelProperty(value = "订单id", required = true, example = "123456")
    private Long orderId;

    /**
     * 商品id
     */
    @NotNull(message = "skuId 不为null")
    @ApiModelProperty(value = "商品id", required = true, example = "123456")
    private Long skuId;
}
