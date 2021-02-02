package com.wx.service;

import com.wx.domain.order.entity.SkuOrderEntity;
import com.wx.domain.order.service.SkuOrderDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 2:00 下午
 * ---------------------------------------
 * Desc    : 商品订单
 */
@Service
public class SkuOrderFacade {

    @Autowired
    private SkuOrderDomainService skuOrderDomainService;

    /**
     * 创建出库/入库订单
     */
    public long insertSkuOrder(SkuOrderEntity skuOrderEntity) {
        return skuOrderDomainService.insertSkuOrder(skuOrderEntity);
    }
}
