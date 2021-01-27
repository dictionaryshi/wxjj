package com.wx.domain.stock.factory;

import com.wx.dao.warehouse.model.StockBaseInfoDO;
import com.wx.domain.stock.entity.StockBaseInfoEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

/**
 * @author : shichunyang
 * Date    : 2021/1/27
 * Time    : 1:31 下午
 * ---------------------------------------
 * Desc    : 仓库基本信息工厂
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StockBaseInfoFactory {

    public static Optional<StockBaseInfoEntity> toStockBaseInfoEntity(StockBaseInfoDO stockBaseInfoDO) {
        if (Objects.isNull(stockBaseInfoDO)) {
            return Optional.empty();
        }

        StockBaseInfoEntity stockBaseInfoEntity = new StockBaseInfoEntity();
        stockBaseInfoEntity.setId(stockBaseInfoDO.getId());
        stockBaseInfoEntity.setName(stockBaseInfoDO.getName());
        stockBaseInfoEntity.setAddress(stockBaseInfoDO.getAddress());
        return Optional.of(stockBaseInfoEntity);
    }

    public static Optional<StockBaseInfoDO> toStockBaseInfoDO(StockBaseInfoEntity stockBaseInfoEntity) {
        if (Objects.isNull(stockBaseInfoEntity)) {
            return Optional.empty();
        }

        StockBaseInfoDO stockBaseInfoDO = new StockBaseInfoDO();
        stockBaseInfoDO.setId(stockBaseInfoEntity.getId());
        stockBaseInfoDO.setName(stockBaseInfoEntity.getName());
        stockBaseInfoDO.setAddress(stockBaseInfoEntity.getAddress());
        return Optional.of(stockBaseInfoDO);
    }
}
