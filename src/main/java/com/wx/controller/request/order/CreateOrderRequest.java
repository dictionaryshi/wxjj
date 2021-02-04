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
 * Time    : 2:09 下午
 * ---------------------------------------
 * Desc    : 创建订单请求
 */
@ApiModel("创建订单请求")
@Getter
@Setter
@ToString
public class CreateOrderRequest {

    /**
     * 仓库id
     */
    @NotNull(message = "stockBaseInfoId 不为null")
    @ApiModelProperty(value = "仓库id", required = true, example = "123456")
    private Long stockBaseInfoId;

    /**
     * 订单类型
     */
    @NotNull(message = "订单类型 不为null")
    @ApiModelProperty(value = "订单类型(1:入库, 2:出库)", required = true, example = "1")
    private Integer type;

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
