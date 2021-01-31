package com.wx.dao.warehouse.model;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SkuStockDetailDO {
    /**
     * id
     */
    private Long id;

    /**
     * 仓库id
     */
    private Long stockBaseInfoId;

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 操作类型
     */
    private Integer type;

    /**
     * 库存操作值
     */
    private Long stockOffset;

    /**
     * 操作前库存
     */
    private Long stockBefore;

    /**
     * 操作后库存
     */
    private Long stockAfter;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 操作人
     */
    private Long operator;

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

    public void setStockBaseInfoId(Long stockBaseInfoId) {
        this.stockBaseInfoId = stockBaseInfoId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setStockOffset(Long stockOffset) {
        this.stockOffset = stockOffset;
    }

    public void setStockBefore(Long stockBefore) {
        this.stockBefore = stockBefore;
    }

    public void setStockAfter(Long stockAfter) {
        this.stockAfter = stockAfter;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
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