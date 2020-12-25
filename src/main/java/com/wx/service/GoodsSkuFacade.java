package com.wx.service;

import com.scy.core.ObjectUtil;
import com.scy.core.exception.BusinessException;
import com.scy.core.format.MessageUtil;
import com.scy.core.model.DiffBO;
import com.wx.domain.category.entity.SkuCategoryEntity;
import com.wx.domain.category.service.SkuCategoryDomainService;
import com.wx.domain.sku.entity.GoodsSkuEntity;
import com.wx.domain.sku.service.GoodsSkuDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : shichunyang
 * Date    : 2020/12/25
 * Time    : 11:31 上午
 * ---------------------------------------
 * Desc    : 商品应用服务
 */
@Slf4j
@Service
public class GoodsSkuFacade {

    @Autowired
    private SkuCategoryDomainService skuCategoryDomainService;

    @Autowired
    private GoodsSkuDomainService goodsSkuDomainService;

    public long add(GoodsSkuEntity goodsSkuEntity) {
        checkCategory(goodsSkuEntity);

        return goodsSkuDomainService.add(goodsSkuEntity);
    }

    public List<DiffBO> update(GoodsSkuEntity goodsSkuEntity) {
        checkCategory(goodsSkuEntity);

        return goodsSkuDomainService.update(goodsSkuEntity);
    }

    /**
     * 查询商品
     */
    public GoodsSkuEntity getGoodsSkuEntity(long skuId) {
        GoodsSkuEntity goodsSkuEntity = goodsSkuDomainService.getGoodsSkuEntity(skuId);
        if (ObjectUtil.isNull(goodsSkuEntity)) {
            return null;
        }

        String skuCategoryName = skuCategoryDomainService.getSkuCategoryName(goodsSkuEntity.getCategoryId());
        goodsSkuEntity.setCategoryName(skuCategoryName);
        return goodsSkuEntity;
    }

    private void checkCategory(GoodsSkuEntity goodsSkuEntity) {
        SkuCategoryEntity skuCategoryEntity = skuCategoryDomainService.getSkuCategoryEntity(goodsSkuEntity.getCategoryId());
        if (ObjectUtil.isNull(skuCategoryEntity)) {
            throw new BusinessException(MessageUtil.format("品类不存在", "categoryId", goodsSkuEntity.getCategoryId()));
        }
    }
}
