package com.wx.controller.response.order;

import com.scy.core.format.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author : shichunyang
 * Date    : 2021/2/3
 * Time    : 6:15 下午
 * ---------------------------------------
 * Desc    : 订单条目响应
 */
@ApiModel("订单条目响应")
@Getter
@Setter
@ToString
public class OrderItemResponse {

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id", required = true, example = "123456")
    private String orderId;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id", required = true, example = "123456")
    private Long skuId;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", required = true, example = "10")
    private Long number;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true, example = DateUtil.DEFAULT_TIME)
    private Date createdAt;


    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", required = false, example = "桌子")
    private String skuName;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态(0:未提交, 1:已提交)", required = false, example = "1")
    private Integer status;

    /**
     * 订单状态描述
     */
    @ApiModelProperty(value = "订单状态描述", required = true, example = "未提交/已提交")
    private String statusDesc;
}
