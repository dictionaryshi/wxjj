package com.wx.dao.warehouse.model;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SkuStockDO {
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
     * 库存
     */
    private Long stock;

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

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}