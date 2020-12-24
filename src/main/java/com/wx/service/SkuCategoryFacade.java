package com.wx.service;

import com.scy.core.model.DiffBO;
import com.wx.domain.category.entity.SkuCategoryEntity;
import com.wx.domain.category.service.SkuCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 查询品类
     */
    public SkuCategoryEntity getSkuCategoryEntity(long categoryId) {
        return skuCategoryDomainService.getSkuCategoryEntity(categoryId);
    }

    /**
     * 查询所有品类
     */
    public List<SkuCategoryEntity> listAllSkuCategoryEntities() {
        return skuCategoryDomainService.listAllSkuCategoryEntities();
    }

    /**
     * 修改品类
     */
    public List<DiffBO> updateSkuCategory(long categoryId, String categoryName) {
        return skuCategoryDomainService.updateSkuCategory(categoryId, categoryName);
    }
}
