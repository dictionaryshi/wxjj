package com.wx.domain.sku.service;

import com.scy.core.CollectionUtil;
import com.scy.core.ObjectUtil;
import com.scy.core.exception.BusinessException;
import com.scy.core.format.MessageUtil;
import com.wx.dao.warehouse.mapper.GoodsSkuDOMapper;
import com.wx.dao.warehouse.model.GoodsSkuDO;
import com.wx.dao.warehouse.model.GoodsSkuDOExample;
import com.wx.domain.sku.entity.GoodsSkuEntity;
import com.wx.domain.sku.factory.GoodsSkuFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public long add(GoodsSkuEntity goodsSkuEntity) {
        if (!ObjectUtil.isNull(getGoodsSkuEntity(goodsSkuEntity.getSkuName()))) {
            throw new BusinessException(MessageUtil.format("商品已存在", "skuName", goodsSkuEntity.getSkuName()));
        }

        GoodsSkuDO goodsSkuDO = GoodsSkuFactory.toGoodsSkuDO(goodsSkuEntity);
        goodsSkuDOMapper.insertSelective(goodsSkuDO);
        return goodsSkuDO.getId();
    }
}
