package com.wx.controller.assembler;

import com.wx.controller.request.stock.AddStockBaseInfoRequest;
import com.wx.controller.request.stock.UpdateStockBaseInfoRequest;
import com.wx.controller.response.stock.StockBaseInfoResponse;
import com.wx.domain.stock.entity.StockBaseInfoEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

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

    public static Optional<StockBaseInfoResponse> toStockBaseInfoResponse(StockBaseInfoEntity stockBaseInfoEntity) {
        if (Objects.isNull(stockBaseInfoEntity)) {
            return Optional.empty();
        }

        StockBaseInfoResponse stockBaseInfoResponse = new StockBaseInfoResponse();
        stockBaseInfoResponse.setId(stockBaseInfoEntity.getId());
        stockBaseInfoResponse.setName(stockBaseInfoEntity.getName());
        stockBaseInfoResponse.setAddress(stockBaseInfoEntity.getAddress());
        return Optional.of(stockBaseInfoResponse);
    }

    public static StockBaseInfoEntity toStockBaseInfoEntity(UpdateStockBaseInfoRequest updateStockBaseInfoRequest) {
        StockBaseInfoEntity stockBaseInfoEntity = new StockBaseInfoEntity();
        stockBaseInfoEntity.setId(updateStockBaseInfoRequest.getId());
        stockBaseInfoEntity.setName(updateStockBaseInfoRequest.getName());
        stockBaseInfoEntity.setAddress(updateStockBaseInfoRequest.getAddress());
        return stockBaseInfoEntity;
    }
}
