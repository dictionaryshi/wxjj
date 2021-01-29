package com.wx.domain.stock.factory;

import com.wx.dao.warehouse.model.SkuStockDO;
import com.wx.domain.stock.entity.SkuStockEntity;
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
}
