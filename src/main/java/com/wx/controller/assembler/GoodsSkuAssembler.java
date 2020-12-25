package com.wx.controller.assembler;

import com.wx.controller.request.goods.AddGoodsSkuRequest;
import com.wx.controller.request.goods.UpdateGoodsSkuRequest;
import com.wx.domain.sku.entity.GoodsSkuEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author : shichunyang
 * Date    : 2020/12/25
 * Time    : 11:53 上午
 * ---------------------------------------
 * Desc    : 商品对象工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodsSkuAssembler {

    public static GoodsSkuEntity toGoodsSkuEntity(AddGoodsSkuRequest addGoodsSkuRequest) {
        GoodsSkuEntity goodsSkuEntity = new GoodsSkuEntity();
        goodsSkuEntity.setSkuName(addGoodsSkuRequest.getSkuName());
        goodsSkuEntity.setCategoryId(addGoodsSkuRequest.getCategoryId());
        return goodsSkuEntity;
    }

    public static GoodsSkuEntity toGoodsSkuEntity(UpdateGoodsSkuRequest updateGoodsSkuRequest) {
        GoodsSkuEntity goodsSkuEntity = new GoodsSkuEntity();
        goodsSkuEntity.setSkuId(updateGoodsSkuRequest.getSkuId());
        goodsSkuEntity.setSkuName(updateGoodsSkuRequest.getSkuName());
        goodsSkuEntity.setCategoryId(updateGoodsSkuRequest.getCategoryId());
        return goodsSkuEntity;
    }
}
