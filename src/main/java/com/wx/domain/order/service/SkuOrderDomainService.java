package com.wx.domain.order.service;

import com.scy.core.CollectionUtil;
import com.wx.dao.warehouse.mapper.extend.SkuOrderDOMapperExtend;
import com.wx.dao.warehouse.model.SkuOrderDO;
import com.wx.dao.warehouse.model.SkuOrderDOExample;
import com.wx.domain.order.entity.SkuOrderEntity;
import com.wx.domain.order.factory.SkuOrderFactory;
import com.wx.infrastructure.service.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 11:52 上午
 * ---------------------------------------
 * Desc    : 商品订单领域服务
 */
@Service
public class SkuOrderDomainService {

    @Autowired
    private SkuOrderDOMapperExtend skuOrderDOMapper;

    @Autowired
    private IdService idService;

    public long insertSkuOrder(SkuOrderEntity skuOrderEntity) {
        skuOrderEntity.setOrderId(idService.getId());
        skuOrderEntity.waitConfirm();

        SkuOrderDO skuOrderDO = SkuOrderFactory.toSkuOrderDO(skuOrderEntity);
        skuOrderDOMapper.insertSelective(skuOrderDO);
        return skuOrderDO.getOrderId();
    }

    public Optional<SkuOrderEntity> getOrder(long orderId) {
        SkuOrderDOExample skuOrderDOExample = new SkuOrderDOExample();
        SkuOrderDOExample.Criteria criteria = skuOrderDOExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);

        List<SkuOrderDO> skuOrders = skuOrderDOMapper.selectByExample(skuOrderDOExample);
        SkuOrderDO skuOrderDO = CollectionUtil.firstElement(skuOrders);
        return Optional.ofNullable(skuOrderDO).flatMap(SkuOrderFactory::toSkuOrderEntity);
    }
}
