package com.wx.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * RedisKeyEnum
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
@Getter
@ToString
@AllArgsConstructor
public enum RedisKeyEnum {

    /**
     * 图片验证码
     */
    SSO_CAPTCHA("sso_captcha", "图片验证码"),
    LOGIN_TOKEN("login_token", "登陆token"),
    SKU_STOCK_LOCK("sku_stock_lock", "库存分布式锁"),
    SKU_ORDER_LOCK("sku_order_lock", "商品订单分布式锁"),
    JOB_REGISTRY_LOCK("job_registry_lock", "job 注册更新"),
    ;

    private final String redisKeyPrefix;

    private final String description;
}
