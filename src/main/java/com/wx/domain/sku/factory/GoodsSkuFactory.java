package com.wx.domain.sku.factory;

import com.scy.core.ObjectUtil;
import com.wx.dao.warehouse.model.GoodsSkuDO;
import com.wx.domain.sku.entity.GoodsSkuEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author : shichunyang
 * Date    : 2020/12/25
 * Time    : 11:06 上午
 * ---------------------------------------
 * Desc    : 商品工厂类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodsSkuFactory {

    public static GoodsSkuEntity toGoodsSkuEntity(GoodsSkuDO goodsSkuDO) {
        if (ObjectUtil.isNull(goodsSkuDO)) {
            return null;
        }

        GoodsSkuEntity goodsSkuEntity = new GoodsSkuEntity();
        goodsSkuEntity.setSkuId(goodsSkuDO.getId());
        goodsSkuEntity.setSkuName(goodsSkuDO.getSkuName());
        goodsSkuEntity.setCategoryId(goodsSkuDO.getCategoryId());
        return goodsSkuEntity;
    }
}
