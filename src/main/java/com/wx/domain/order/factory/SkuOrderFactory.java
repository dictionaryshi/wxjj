package com.wx.domain.order.factory;

import com.wx.dao.warehouse.model.SkuOrderDO;
import com.wx.domain.order.entity.SkuOrderEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 11:54 上午
 * ---------------------------------------
 * Desc    : 商品订单工厂
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkuOrderFactory {

    public static SkuOrderDO toSkuOrderDO(SkuOrderEntity skuOrderEntity) {
        SkuOrderDO skuOrderDO = new SkuOrderDO();
        skuOrderDO.setOrderId(skuOrderEntity.getOrderId());
        skuOrderDO.setType(skuOrderEntity.getType());
        skuOrderDO.setStatus(skuOrderEntity.getStatus());
        skuOrderDO.setConfirmTime(skuOrderEntity.getConfirmTime());
        skuOrderDO.setOperator(skuOrderEntity.getOperator());
        skuOrderDO.setPrice(skuOrderEntity.getPrice());
        skuOrderDO.setCustomerName(skuOrderEntity.getCustomerName());
        skuOrderDO.setCustomerPhone(skuOrderEntity.getCustomerPhone());
        skuOrderDO.setCustomerAddress(skuOrderEntity.getCustomerAddress());
        skuOrderDO.setRemark(skuOrderEntity.getRemark());
        return skuOrderDO;
    }
}
