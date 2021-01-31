package com.wx.domain.stock.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2021/1/29
 * Time    : 4:55 下午
 * ---------------------------------------
 * Desc    : 库存操作值对象
 */
@Getter
@Setter
@ToString
public class StockOperateValueobject {

    /**
     * 订单id
     */
    private Long orderId;

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
}
