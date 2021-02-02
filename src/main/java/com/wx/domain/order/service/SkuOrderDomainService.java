package com.wx.domain.order.service;

import com.wx.dao.warehouse.mapper.extend.SkuOrderDOMapperExtend;
import com.wx.dao.warehouse.model.SkuOrderDO;
import com.wx.domain.order.entity.SkuOrderEntity;
import com.wx.domain.order.factory.SkuOrderFactory;
import com.wx.infrastructure.service.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
