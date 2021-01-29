package com.wx.domain.stock.entity;

import com.scy.core.StringUtil;
import com.scy.core.format.NumberUtil;
import com.scy.redis.util.RedisUtil;
import com.wx.constant.RedisKeyEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2021/1/29
 * Time    : 4:45 下午
 * ---------------------------------------
 * Desc    : 商品库存
 */
@Getter
@Setter
@ToString
public class SkuStockEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 仓库id
     */
    private Long stockBaseInfoId;

    /**
     * 仓库名称
     */
    private String stockName;

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 库存
     */
    private Long stock;

    /**
     * 库存操作
     */
    private StockOperateValueobject stockOperateValueobject;

    public void operateStock(long stockOffset) {
        this.stockOperateValueobject = new StockOperateValueobject();
        this.stockOperateValueobject.setStockOffset(NumberUtil.abs(stockOffset));
    }

    public void operateStockAfter(Long stockBefore, Long stockAfter) {
        this.stockOperateValueobject.setStockBefore(stockBefore);
        this.stockOperateValueobject.setStockAfter(stockAfter);
    }

    public String getLockKey() {
        return RedisUtil.getRedisKey(RedisKeyEnum.SKU_STOCK_LOCK.getRedisKeyPrefix(), stockBaseInfoId + StringUtil.POINT + skuId);
    }
}
