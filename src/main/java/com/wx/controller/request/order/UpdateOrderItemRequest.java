package com.wx.controller.request.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2021/2/3
 * Time    : 8:11 下午
 * ---------------------------------------
 * Desc    : 修改订单条目请求
 */
@ApiModel("修改订单条目请求")
@Getter
@Setter
@ToString
public class UpdateOrderItemRequest {

    /**
     * 订单id
     */
    @NotBlank(message = "orderId 不为null")
    @ApiModelProperty(value = "订单id", required = true, example = "123456")
    private String orderId;

    /**
     * 商品id
     */
    @NotNull(message = "skuId 不为null")
    @ApiModelProperty(value = "商品id", required = true, example = "123456")
    private Long skuId;

    /**
     * 数量
     */
    @NotNull(message = "number 不为null")
    @ApiModelProperty(value = "数量", required = true, example = "10")
    private Long number;
}
