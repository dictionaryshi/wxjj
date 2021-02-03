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
 * Time    : 6:12 下午
 * ---------------------------------------
 * Desc    : 查询订单条目请求
 */
@ApiModel("查询订单条目请求")
@Getter
@Setter
@ToString
public class QueryOrderItemRequest {

    @NotNull(message = "orderId 不为null")
    @ApiModelProperty(value = "订单id", required = true, example = "123456")
    private Long orderId;
}
