package com.wx.controller.assembler;

import com.wx.controller.request.stock.UpdateStockRequest;
import com.wx.domain.stock.entity.SkuStockEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author : shichunyang
 * Date    : 2021/1/30
 * Time    : 9:23 下午
 * ---------------------------------------
 * Desc    : 商品库存Assembler
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkuStockAssembler {

    public static SkuStockEntity toSkuStockEntity(UpdateStockRequest updateStockRequest) {
        SkuStockEntity skuStockEntity = new SkuStockEntity();
        skuStockEntity.setStockBaseInfoId(updateStockRequest.getStockBaseInfoId());
        skuStockEntity.setSkuId(updateStockRequest.getSkuId());
        skuStockEntity.setStock(updateStockRequest.getStock());
        return skuStockEntity;
    }
}
