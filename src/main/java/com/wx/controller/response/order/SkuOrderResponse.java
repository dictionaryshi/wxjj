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
 * Date    : 2021/2/2
 * Time    : 5:04 下午
 * ---------------------------------------
 * Desc    : 商品订单响应
 */
@ApiModel("商品订单响应")
@Getter
@Setter
@ToString
public class SkuOrderResponse {

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id", required = true, example = "123456")
    private Long orderId;

    /**
     * 金额(元)
     */
    @ApiModelProperty(value = "金额(元)", required = false, example = "325.32")
    private Double price;

    /**
     * 客户姓名
     */
    @ApiModelProperty(value = "客户姓名", required = false, example = "徐婷")
    private String customerName;

    /**
     * 客户电话
     */
    @ApiModelProperty(value = "客户电话", required = false, example = "13264232894")
    private String customerPhone;

    /**
     * 客户地址
     */
    @ApiModelProperty(value = "客户地址", required = false, example = "黑龙江省鹤岗市")
    private String customerAddress;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false, example = "客户很着急")
    private String remark;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true, example = DateUtil.DEFAULT_TIME)
    private Date createdAt;


    /**
     * 仓库名称
     */
    @ApiModelProperty(value = "仓库名称", required = false, example = "北京仓库")
    private String stockBaseInfoName;

    /**
     * 订单类型描述
     */
    @ApiModelProperty(value = "订单类型描述", required = true, example = "出库/入库")
    private String typeDesc;

    /**
     * 订单状态描述
     */
    @ApiModelProperty(value = "订单状态描述", required = true, example = "未提交/已提交")
    private String statusDesc;

    /**
     * 订单提交时间(格式化)
     */
    @ApiModelProperty(value = "订单提交时间", required = false, example = DateUtil.DEFAULT_TIME)
    private Date confirmTimeDate;

    /**
     * 操作人姓名
     */
    @ApiModelProperty(value = "操作人姓名", required = true, example = "徐婷")
    private String operatorName;
}
