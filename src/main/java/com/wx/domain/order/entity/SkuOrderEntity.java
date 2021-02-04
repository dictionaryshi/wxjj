package com.wx.domain.order.entity;

import com.scy.redis.util.RedisUtil;
import com.scy.web.util.LoginUtil;
import com.wx.constant.RedisKeyEnum;
import com.wx.domain.order.entity.valueobject.OrderStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 11:26 上午
 * ---------------------------------------
 * Desc    : 商品订单实体
 */
@Getter
@Setter
@ToString
public class SkuOrderEntity {

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
     * 金额(分)
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
     * 订单类型描述
     */
    private String typeDesc;

    /**
     * 订单状态描述
     */
    private String statusDesc;

    /**
     * 订单提交时间(格式化)
     */
    private Date confirmTimeDate;

    /**
     * 操作人姓名
     */
    private String operatorName;


    /**
     * 开始时间
     */
    private Date createdAtStart;

    /**
     * 结束时间
     */
    private Date createdAtEnd;

    /**
     * 待提交
     */
    public void waitConfirm() {
        this.status = OrderStatusEnum.WAIT_TO_CONFIRMED.getStatus();
    }

    /**
     * 提交
     */
    public void confirm() {
        this.status = OrderStatusEnum.HAS_BEEN_CONFIRMED.getStatus();
        this.confirmTime = System.currentTimeMillis();
        this.operator = LoginUtil.getLoginUser().getUserId();
    }

    public String getLockKey() {
        return RedisUtil.getRedisKey(RedisKeyEnum.SKU_ORDER_LOCK.getRedisKeyPrefix(), String.valueOf(orderId));
    }
}
