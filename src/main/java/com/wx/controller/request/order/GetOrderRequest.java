package com.wx.controller.request.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 5:00 下午
 * ---------------------------------------
 * Desc    : 查询订单请求
 */
@ApiModel("查询订单请求")
@Getter
@Setter
@ToString
public class GetOrderRequest {

    @NotNull(message = "orderId 不为null")
    @ApiModelProperty(value = "订单id", required = true, example = "123456")
    private Long orderId;
}
