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

    @NotBlank(message = "orderId 不为空")
    @ApiModelProperty(value = "订单id", required = true, example = "123456")
    private String orderId;
}
