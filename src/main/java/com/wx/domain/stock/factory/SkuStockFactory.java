package com.wx.domain.stock.factory;

import com.scy.web.util.LoginUtil;
import com.wx.dao.warehouse.model.SkuStockDO;
import com.wx.dao.warehouse.model.SkuStockDetailDO;
import com.wx.domain.stock.entity.SkuStockDetailEntity;
import com.wx.domain.stock.entity.SkuStockEntity;
import com.wx.domain.stock.entity.StockOperateValueobject;
import com.wx.domain.stock.entity.valueobject.StockTypeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

/**
 * @author : shichunyang
 * Date    : 2021/1/29
 * Time    : 7:38 下午
 * ---------------------------------------
 * Desc    : 商品库存工厂
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkuStockFactory {

    public static Optional<SkuStockDO> toSkuStockDO(SkuStockEntity skuStockEntity) {
        if (Objects.isNull(skuStockEntity)) {
            return Optional.empty();
        }

        SkuStockDO skuStockDO = new SkuStockDO();
        skuStockDO.setId(skuStockEntity.getId());
        skuStockDO.setStockBaseInfoId(skuStockEntity.getStockBaseInfoId());
        skuStockDO.setSkuId(skuStockEntity.getSkuId());
        skuStockDO.setStock(skuStockEntity.getStock());
        return Optional.of(skuStockDO);
    }

    public static Optional<SkuStockEntity> toSkuStockEntity(SkuStockDO skuStockDO) {
        if (Objects.isNull(skuStockDO)) {
            return Optional.empty();
        }

        SkuStockEntity skuStockEntity = new SkuStockEntity();
        skuStockEntity.setId(skuStockDO.getId());
        skuStockEntity.setStockBaseInfoId(skuStockDO.getStockBaseInfoId());
        skuStockEntity.setSkuId(skuStockDO.getSkuId());
        skuStockEntity.setStock(skuStockDO.getStock());
        return Optional.of(skuStockEntity);
    }

    public static SkuStockDetailDO toSkuStockDetailDO(SkuStockEntity skuStockEntity, StockTypeEnum stockTypeEnum) {
        StockOperateValueobject stockOperateValueobject = skuStockEntity.getStockOperateValueobject();

        SkuStockDetailDO skuStockDetailDO = new SkuStockDetailDO();
        skuStockDetailDO.setStockBaseInfoId(skuStockEntity.getStockBaseInfoId());
        skuStockDetailDO.setSkuId(skuStockEntity.getSkuId());
        skuStockDetailDO.setType(stockTypeEnum.getType());
        skuStockDetailDO.setStockOffset(stockOperateValueobject.getStockOffset());
        skuStockDetailDO.setStockBefore(stockOperateValueobject.getStockBefore());
        skuStockDetailDO.setStockAfter(stockOperateValueobject.getStockAfter());
        skuStockDetailDO.setOrderId(stockOperateValueobject.getOrderId());
        skuStockDetailDO.setOperator(LoginUtil.getLoginUser().getUserId());
        return skuStockDetailDO;
    }

    public static SkuStockDetailEntity toSkuStockDetailEntity(SkuStockDetailDO skuStockDetailDO) {
        SkuStockDetailEntity skuStockDetailEntity = new SkuStockDetailEntity();
        skuStockDetailEntity.setStockBaseInfoId(skuStockDetailDO.getStockBaseInfoId());
        skuStockDetailEntity.setSkuId(skuStockDetailDO.getSkuId());
        skuStockDetailEntity.setType(skuStockDetailDO.getType());
        skuStockDetailEntity.setStockOffset(skuStockDetailDO.getStockOffset());
        skuStockDetailEntity.setStockBefore(skuStockDetailDO.getStockBefore());
        skuStockDetailEntity.setStockAfter(skuStockDetailDO.getStockAfter());
        skuStockDetailEntity.setOrderId(skuStockDetailDO.getOrderId());
        skuStockDetailEntity.setOperator(skuStockDetailDO.getOperator());
        skuStockDetailEntity.setCreatedAt(skuStockDetailDO.getCreatedAt());
        skuStockDetailEntity.setTypeDesc(StockTypeEnum.getByType(skuStockDetailDO.getType()));
        return skuStockDetailEntity;
    }
}
