package com.wx.controller.assembler;

import com.scy.core.format.NumberUtil;
import com.scy.web.util.LoginUtil;
import com.wx.controller.request.order.CreateOrderRequest;
import com.wx.domain.order.entity.SkuOrderEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 2:27 下午
 * ---------------------------------------
 * Desc    : 商品订单Assembler
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkuOrderAssembler {

    public static SkuOrderEntity toSkuOrderEntity(CreateOrderRequest createOrderRequest) {
        SkuOrderEntity skuOrderEntity = new SkuOrderEntity();
        skuOrderEntity.setType(createOrderRequest.getType());
        skuOrderEntity.setOperator(LoginUtil.getLoginUser().getUserId());
        if (!Objects.isNull(createOrderRequest.getPrice())) {
            skuOrderEntity.setPrice(NumberUtil.yuanToFen(createOrderRequest.getPrice()));
        }
        skuOrderEntity.setCustomerName(createOrderRequest.getCustomerName());
        skuOrderEntity.setCustomerPhone(createOrderRequest.getCustomerPhone());
        skuOrderEntity.setCustomerAddress(createOrderRequest.getCustomerAddress());
        skuOrderEntity.setRemark(createOrderRequest.getRemark());
        return skuOrderEntity;
    }
}
