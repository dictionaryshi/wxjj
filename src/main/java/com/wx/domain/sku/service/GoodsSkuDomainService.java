package com.wx.domain.sku.service;

import com.scy.core.CollectionUtil;
import com.scy.core.DiffUtil;
import com.scy.core.ObjectUtil;
import com.scy.core.StringUtil;
import com.scy.core.exception.BusinessException;
import com.scy.core.format.MessageUtil;
import com.scy.core.model.DiffBO;
import com.scy.core.page.PageParam;
import com.scy.core.page.PageResult;
import com.scy.db.util.ForceMasterHelper;
import com.wx.dao.warehouse.mapper.GoodsSkuDOMapper;
import com.wx.dao.warehouse.model.GoodsSkuDO;
import com.wx.dao.warehouse.model.GoodsSkuDOExample;
import com.wx.domain.sku.entity.GoodsSkuEntity;
import com.wx.domain.sku.factory.GoodsSkuFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2020/12/25
 * Time    : 11:10 上午
 * ---------------------------------------
 * Desc    : 商品领域服务
 */
@Slf4j
@Service
public class GoodsSkuDomainService {

    @Autowired
    private GoodsSkuDOMapper goodsSkuDOMapper;

    public GoodsSkuEntity getGoodsSkuEntity(long skuId) {
        GoodsSkuDO goodsSkuDO = goodsSkuDOMapper.selectByPrimaryKey(skuId);
        return GoodsSkuFactory.toGoodsSkuEntity(goodsSkuDO);
    }

    public GoodsSkuEntity getGoodsSkuEntity(String skuName) {
        GoodsSkuDOExample goodsSkuDOExample = new GoodsSkuDOExample();
        GoodsSkuDOExample.Criteria criteria = goodsSkuDOExample.createCriteria();
        criteria.andSkuNameEqualTo(skuName);
        List<GoodsSkuDO> goodsSkus = goodsSkuDOMapper.selectByExample(goodsSkuDOExample);
        GoodsSkuDO goodsSkuDO = CollectionUtil.firstElement(goodsSkus);
        return GoodsSkuFactory.toGoodsSkuEntity(goodsSkuDO);
    }

    public List<Long> listIdsByName(String skuName) {
        GoodsSkuDOExample goodsSkuDOExample = new GoodsSkuDOExample();
        GoodsSkuDOExample.Criteria criteria = goodsSkuDOExample.createCriteria();
        criteria.andSkuNameLike(skuName + StringUtil.PERCENT);
        List<GoodsSkuDO> goodsSkus = goodsSkuDOMapper.selectByExample(goodsSkuDOExample);
        return goodsSkus.stream().map(GoodsSkuDO::getId).collect(Collectors.toList());
    }

    public long add(GoodsSkuEntity goodsSkuEntity) {
        if (!ObjectUtil.isNull(getGoodsSkuEntity(goodsSkuEntity.getSkuName()))) {
            throw new BusinessException(MessageUtil.format("商品已存在", "skuName", goodsSkuEntity.getSkuName()));
        }

        GoodsSkuDO goodsSkuDO = GoodsSkuFactory.toGoodsSkuDO(goodsSkuEntity);
        goodsSkuDOMapper.insertSelective(goodsSkuDO);
        return goodsSkuDO.getId();
    }

    public List<DiffBO> update(GoodsSkuEntity goodsSkuEntity) {
        GoodsSkuEntity goodsSkuEntityBySkuId = getGoodsSkuEntity(goodsSkuEntity.getSkuId());
        if (ObjectUtil.isNull(goodsSkuEntityBySkuId)) {
            throw new BusinessException(MessageUtil.format("商品不存在", "skuId", goodsSkuEntity.getSkuId()));
        }

        if (!ObjectUtil.equals(goodsSkuEntity.getSkuName(), goodsSkuEntityBySkuId.getSkuName())
                && !ObjectUtil.isNull(getGoodsSkuEntity(goodsSkuEntity.getSkuName()))) {
            throw new BusinessException(MessageUtil.format("商品已存在", "skuName", goodsSkuEntity.getSkuName()));
        }

        GoodsSkuDO goodsSkuDO = GoodsSkuFactory.toGoodsSkuDO(goodsSkuEntity);
        goodsSkuDOMapper.updateByPrimaryKeySelective(goodsSkuDO);

        ForceMasterHelper.forceMaster();
        GoodsSkuEntity afterGoodsSkuEntity = getGoodsSkuEntity(goodsSkuEntity.getSkuId());
        ForceMasterHelper.clearForceMaster();

        return DiffUtil.diff(goodsSkuEntityBySkuId, afterGoodsSkuEntity);
    }

    public List<Long> listCategoryIds(List<GoodsSkuEntity> goodsSkuEntities) {
        return CollectionUtil.map(goodsSkuEntities, GoodsSkuEntity::getCategoryId)
                .distinct().collect(Collectors.toList());
    }

    public List<GoodsSkuEntity> listByCategoryId(long categoryId) {
        GoodsSkuDOExample goodsSkuDOExample = new GoodsSkuDOExample();
        GoodsSkuDOExample.Criteria criteria = goodsSkuDOExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<GoodsSkuDO> goodsSkus = goodsSkuDOMapper.selectByExample(goodsSkuDOExample);
        return CollectionUtil.map(goodsSkus, GoodsSkuFactory::toGoodsSkuEntity).collect(Collectors.toList());
    }

    public PageResult<GoodsSkuEntity> listByPage(PageParam pageParam, GoodsSkuEntity goodsSkuEntity) {
        PageResult<GoodsSkuEntity> pageResult = new PageResult<>();
        pageResult.setPage(pageParam.getPage());
        pageResult.setLimit(pageParam.getLimit());

        GoodsSkuDOExample goodsSkuDOExample = new GoodsSkuDOExample();
        goodsSkuDOExample.setOrderByClause("id desc");
        goodsSkuDOExample.setOffset(pageParam.getOffset());
        goodsSkuDOExample.setLimit(pageParam.getLimit());

        GoodsSkuDOExample.Criteria criteria = goodsSkuDOExample.createCriteria();
        if (!ObjectUtil.isNull(goodsSkuEntity.getSkuId())) {
            criteria.andIdEqualTo(goodsSkuEntity.getSkuId());
        }

        if (!StringUtil.isEmpty(goodsSkuEntity.getSkuName())) {
            criteria.andSkuNameLike(goodsSkuEntity.getSkuName() + StringUtil.PERCENT);
        }

        if (!ObjectUtil.isNull(goodsSkuEntity.getCategoryId())) {
            criteria.andCategoryIdEqualTo(goodsSkuEntity.getCategoryId());
        }

        pageResult.setTotal((int) goodsSkuDOMapper.countByExample(goodsSkuDOExample));

        List<GoodsSkuDO> goodsSkus = goodsSkuDOMapper.selectByExample(goodsSkuDOExample);
        List<GoodsSkuEntity> datas = CollectionUtil.map(goodsSkus, GoodsSkuFactory::toGoodsSkuEntity).collect(Collectors.toList());
        pageResult.setDatas(datas);
        return pageResult;
    }

    public List<GoodsSkuEntity> listGoodsSkuEntities(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return CollectionUtil.emptyList();
        }

        GoodsSkuDOExample goodsSkuDOExample = new GoodsSkuDOExample();
        GoodsSkuDOExample.Criteria criteria = goodsSkuDOExample.createCriteria();
        criteria.andIdIn(ids);
        List<GoodsSkuDO> goodsSkus = goodsSkuDOMapper.selectByExample(goodsSkuDOExample);
        return goodsSkus.stream().map(GoodsSkuFactory::toGoodsSkuEntity).collect(Collectors.toList());
    }

    public Map<Long, String> getSkuNameMap(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return CollectionUtil.emptyMap();
        }

        List<GoodsSkuEntity> goodsSkuEntities = listGoodsSkuEntities(ids);
        return goodsSkuEntities.stream().collect(Collectors.toMap(GoodsSkuEntity::getSkuId, GoodsSkuEntity::getSkuName, (oldValue, newValue) -> newValue));
    }
}
