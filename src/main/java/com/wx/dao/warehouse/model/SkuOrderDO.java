package com.wx.dao.warehouse.model;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SkuOrderDO {
    /**
     * id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 仓库id
     */
    private Long stockBaseInfoId;

    /**
     * 订单类型
     */
    private Integer type;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 订单提交时间
     */
    private Long confirmTime;

    /**
     * 操作人
     */
    private Long operator;

    /**
     * 金额
     */
    private Long price;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 客户电话
     */
    private String customerPhone;

    /**
     * 客户地址
     */
    private String customerAddress;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setStockBaseInfoId(Long stockBaseInfoId) {
        this.stockBaseInfoId = stockBaseInfoId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setConfirmTime(Long confirmTime) {
        this.confirmTime = confirmTime;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone == null ? null : customerPhone.trim();
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress == null ? null : customerAddress.trim();
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}