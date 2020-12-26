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
import com.wx.dao.warehouse.mapper.extend.GoodsSkuDOMapperExtend;
import com.wx.dao.warehouse.model.GoodsSkuDO;
import com.wx.dao.warehouse.model.GoodsSkuDOExample;
import com.wx.dao.warehouse.model.extend.GoodsSkuDOExampleExtend;
import com.wx.domain.sku.entity.GoodsSkuEntity;
import com.wx.domain.sku.factory.GoodsSkuFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    private GoodsSkuDOMapperExtend goodsSkuDOMapperExtend;

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

        GoodsSkuDOExampleExtend goodsSkuDOExampleExtend = new GoodsSkuDOExampleExtend();
        pageParam.setOrderField("id");
        pageParam.setDesc(Boolean.TRUE);
        goodsSkuDOExampleExtend.setPageParam(pageParam);
        GoodsSkuDOExample.Criteria criteria = goodsSkuDOExampleExtend.createCriteria();
        if (!ObjectUtil.isNull(goodsSkuEntity.getSkuId())) {
            criteria.andIdEqualTo(goodsSkuEntity.getSkuId());
        }

        if (!StringUtil.isEmpty(goodsSkuEntity.getSkuName())) {
            criteria.andSkuNameLike(goodsSkuEntity.getSkuName() + StringUtil.PERCENT);
        }

        if (!ObjectUtil.isNull(goodsSkuEntity.getCategoryId())) {
            criteria.andCategoryIdEqualTo(goodsSkuEntity.getCategoryId());
        }

        pageResult.setTotal((int) goodsSkuDOMapper.countByExample(goodsSkuDOExampleExtend));

        List<GoodsSkuDO> goodsSkus = goodsSkuDOMapperExtend.selectByExampleExtend(goodsSkuDOExampleExtend);
        List<GoodsSkuEntity> datas = CollectionUtil.map(goodsSkus, GoodsSkuFactory::toGoodsSkuEntity).collect(Collectors.toList());
        pageResult.setDatas(datas);
        return pageResult;
    }
}
