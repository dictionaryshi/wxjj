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
 * Time    : 1:26 下午
 * ---------------------------------------
 * Desc    : 修改订单请求
 */
@ApiModel("修改订单请求")
@Getter
@Setter
@ToString
public class UpdateOrderRequest {

    /**
     * 订单id
     */
    @NotBlank(message = "orderId 不为null")
    @ApiModelProperty(value = "订单id", required = true, example = "123456")
    private String orderId;

    /**
     * 仓库id
     */
    @NotNull(message = "stockBaseInfoId 不为null")
    @ApiModelProperty(value = "仓库id", required = true, example = "123456")
    private Long stockBaseInfoId;

    /**
     * 金额(元)
     */
    @ApiModelProperty(value = "订单总金额(元)", required = false, example = "325.32")
    private Double price;

    /**
     * 客户姓名
     */
    @ApiModelProperty(value = "客户姓名", required = false, example = "徐婷")
    private String customerName;

    /**
     * 客户电话
     */
    @ApiModelProperty(value = "客户手机号", required = false, example = "13264232894")
    private String customerPhone;

    /**
     * 客户地址
     */
    @ApiModelProperty(value = "客户地址", required = false, example = "黑龙江省鹤岗市")
    private String customerAddress;

    /**
     * 备注
     */
    @ApiModelProperty(value = "订单备注", required = false, example = "客户比较着急")
    private String remark;
}
