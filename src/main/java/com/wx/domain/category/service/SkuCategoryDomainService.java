package com.wx.domain.category.service;

import com.scy.core.CollectionUtil;
import com.scy.core.ObjectUtil;
import com.scy.core.exception.BusinessException;
import com.wx.dao.warehouse.mapper.SkuCategoryDOMapper;
import com.wx.dao.warehouse.model.SkuCategoryDO;
import com.wx.dao.warehouse.model.SkuCategoryDOExample;
import com.wx.domain.category.entity.SkuCategoryEntity;
import com.wx.domain.category.factory.SkuCategoryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2020/12/24
 * Time    : 10:00 上午
 * ---------------------------------------
 * Desc    : 商品分类领域服务
 */
@Slf4j
@Service
public class SkuCategoryDomainService {

    @Autowired
    private SkuCategoryDOMapper skuCategoryDOMapper;

    public SkuCategoryEntity getSkuCategoryEntity(long categoryId) {
        SkuCategoryDO skuCategoryDO = skuCategoryDOMapper.selectByPrimaryKey(categoryId);
        return SkuCategoryFactory.toSkuCategoryEntity(skuCategoryDO);
    }

    public SkuCategoryEntity getSkuCategoryEntity(String categoryName) {
        SkuCategoryDOExample skuCategoryDOExample = new SkuCategoryDOExample();
        SkuCategoryDOExample.Criteria criteria = skuCategoryDOExample.createCriteria();
        criteria.andCategoryNameEqualTo(categoryName);
        List<SkuCategoryDO> skuCategories = skuCategoryDOMapper.selectByExample(skuCategoryDOExample);
        SkuCategoryDO skuCategoryDO = CollectionUtil.firstElement(skuCategories);
        return SkuCategoryFactory.toSkuCategoryEntity(skuCategoryDO);
    }

    public List<SkuCategoryEntity> listSkuCategoryEntities(List<Long> categoryIds) {
        SkuCategoryDOExample skuCategoryDOExample = new SkuCategoryDOExample();
        SkuCategoryDOExample.Criteria criteria = skuCategoryDOExample.createCriteria();
        criteria.andIdIn(categoryIds);
        List<SkuCategoryDO> skuCategories = skuCategoryDOMapper.selectByExample(skuCategoryDOExample);
        return CollectionUtil.map(skuCategories, SkuCategoryFactory::toSkuCategoryEntity).collect(Collectors.toList());
    }

    public List<SkuCategoryEntity> listAllSkuCategoryEntities() {
        SkuCategoryDOExample skuCategoryDOExample = new SkuCategoryDOExample();
        SkuCategoryDOExample.Criteria criteria = skuCategoryDOExample.createCriteria();
        criteria.andIdGreaterThan(0L);
        List<SkuCategoryDO> skuCategories = skuCategoryDOMapper.selectByExample(skuCategoryDOExample);
        return CollectionUtil.map(skuCategories, SkuCategoryFactory::toSkuCategoryEntity).collect(Collectors.toList());
    }

    public long insertSkuCategory(String categoryName) {
        SkuCategoryEntity skuCategoryEntity = getSkuCategoryEntity(categoryName);
        if (!ObjectUtil.isNull(skuCategoryEntity)) {
            throw new BusinessException("商品分类已存在");
        }

        SkuCategoryDO skuCategoryDO = new SkuCategoryDO();
        skuCategoryDO.setCategoryName(categoryName);
        skuCategoryDOMapper.insertSelective(skuCategoryDO);
        return skuCategoryDO.getId();
    }
}