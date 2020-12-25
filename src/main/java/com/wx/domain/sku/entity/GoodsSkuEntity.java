package com.wx.domain.sku.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2020/12/25
 * Time    : 10:36 上午
 * ---------------------------------------
 * Desc    : 商品实体
 */
@Getter
@Setter
@ToString
public class GoodsSkuEntity {

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 品类id
     */
    private Long categoryId;

    /**
     * 品类名称
     */
    private String categoryName;
}
