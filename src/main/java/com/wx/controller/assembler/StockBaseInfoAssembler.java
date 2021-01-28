package com.wx.controller.assembler;

import com.wx.controller.request.stock.AddStockBaseInfoRequest;
import com.wx.domain.stock.entity.StockBaseInfoEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author : shichunyang
 * Date    : 2021/1/28
 * Time    : 11:13 上午
 * ---------------------------------------
 * Desc    : 仓库基础信息转换类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StockBaseInfoAssembler {

    public static StockBaseInfoEntity toStockBaseInfoEntity(AddStockBaseInfoRequest addStockBaseInfoRequest) {
        StockBaseInfoEntity stockBaseInfoEntity = new StockBaseInfoEntity();
        stockBaseInfoEntity.setName(addStockBaseInfoRequest.getName());
        stockBaseInfoEntity.setAddress(addStockBaseInfoRequest.getAddress());
        return stockBaseInfoEntity;
    }
}
