package com.wx.service;

import com.wx.domain.category.service.SkuCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : shichunyang
 * Date    : 2020/12/24
 * Time    : 11:30 上午
 * ---------------------------------------
 * Desc    : 商品分类门面
 */
@Slf4j
@Service
public class SkuCategoryFacade {

    @Autowired
    private SkuCategoryDomainService skuCategoryDomainService;

    /**
     * 插入品类
     */
    public long insertSkuCategory(String categoryName) {
        return skuCategoryDomainService.insertSkuCategory(categoryName);
    }
}
