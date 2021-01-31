package com.wx.domain.stock.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author : shichunyang
 * Date    : 2021/1/31
 * Time    : 3:36 下午
 * ---------------------------------------
 * Desc    : 库存操作明细实体
 */
@Getter
@Setter
@ToString
public class SkuStockDetailEntity {

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
     * 创建时间
     */
    private Date createdAt;


    /**
     * 仓库名称
     */
    private String stockBaseInfoName;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 操作类型描述
     */
    private String typeDesc;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * skuId集合
     */
    private List<Long> skuIds;

    /**
     * 开始时间
     */
    private Date createdAtStart;

    /**
     * 结束时间
     */
    private Date createdAtEnd;
}
