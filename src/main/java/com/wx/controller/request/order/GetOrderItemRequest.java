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
 * Date    : 2021/10/24
 * Time    : 2:25 下午
 * ---------------------------------------
 * Desc    : 查询订单项请求
 */
@ApiModel("查询订单项请求")
@Getter
@Setter
@ToString
public class GetOrderItemRequest {

    /**
     * 订单项id
     */
    @NotNull(message = "订单项id 不为null")
    @ApiModelProperty(value = "订单项id", required = true, example = "123456")
    private Long orderItemId;

    /**
     * 订单id
     */
    @NotBlank(message = "orderId 不为空")
    @ApiModelProperty(value = "订单id", required = true, example = "123456")
    private String orderId;
}
