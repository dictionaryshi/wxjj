package com.wx.domain.order.entity;

import com.scy.redis.util.RedisUtil;
import com.wx.constant.RedisKeyEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author : shichunyang
 * Date    : 2021/2/3
 * Time    : 5:32 下午
 * ---------------------------------------
 * Desc    : 订单条目实体
 */
@Getter
@Setter
@ToString
public class OrderItemEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 数量
     */
    private Long number;

    /**
     * 创建时间
     */
    private Date createdAt;


    /**
     * 商品名称
     */
    private String skuName;

    public String getLockKey() {
        return RedisUtil.getRedisKey(RedisKeyEnum.SKU_ORDER_LOCK.getRedisKeyPrefix(), String.valueOf(orderId));
    }
}
