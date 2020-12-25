package com.wx.service;

import com.scy.core.ObjectUtil;
import com.scy.core.exception.BusinessException;
import com.scy.core.format.MessageUtil;
import com.wx.domain.category.entity.SkuCategoryEntity;
import com.wx.domain.category.service.SkuCategoryDomainService;
import com.wx.domain.sku.entity.GoodsSkuEntity;
import com.wx.domain.sku.service.GoodsSkuDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        SkuCategoryEntity skuCategoryEntity = skuCategoryDomainService.getSkuCategoryEntity(goodsSkuEntity.getCategoryId());
        if (ObjectUtil.isNull(skuCategoryEntity)) {
            throw new BusinessException(MessageUtil.format("品类不存在", "categoryId", goodsSkuEntity.getCategoryId()));
        }

        return goodsSkuDomainService.add(goodsSkuEntity);
    }
}
