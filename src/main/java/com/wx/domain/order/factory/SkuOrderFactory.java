package com.wx.domain.order.factory;

import com.wx.dao.warehouse.model.OrderItemDO;
import com.wx.dao.warehouse.model.SkuOrderDO;
import com.wx.domain.order.entity.OrderItemEntity;
import com.wx.domain.order.entity.SkuOrderEntity;
import com.wx.domain.order.entity.valueobject.OrderStatusEnum;
import com.wx.domain.stock.entity.valueobject.StockTypeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

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
        skuOrderDO.setStockBaseInfoId(skuOrderEntity.getStockBaseInfoId());
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

    public static Optional<SkuOrderEntity> toSkuOrderEntity(SkuOrderDO skuOrderDO) {
        if (Objects.isNull(skuOrderDO)) {
            return Optional.empty();
        }

        SkuOrderEntity skuOrderEntity = new SkuOrderEntity();
        skuOrderEntity.setOrderId(skuOrderDO.getOrderId());
        skuOrderEntity.setStockBaseInfoId(skuOrderDO.getStockBaseInfoId());
        skuOrderEntity.setType(skuOrderDO.getType());
        skuOrderEntity.setStatus(skuOrderDO.getStatus());
        skuOrderEntity.setConfirmTime(skuOrderDO.getConfirmTime());
        skuOrderEntity.setOperator(skuOrderDO.getOperator());
        skuOrderEntity.setPrice(skuOrderDO.getPrice());
        skuOrderEntity.setCustomerName(skuOrderDO.getCustomerName());
        skuOrderEntity.setCustomerPhone(skuOrderDO.getCustomerPhone());
        skuOrderEntity.setCustomerAddress(skuOrderDO.getCustomerAddress());
        skuOrderEntity.setRemark(skuOrderDO.getRemark());
        skuOrderEntity.setCreatedAt(skuOrderDO.getCreatedAt());
        skuOrderEntity.setTypeDesc(StockTypeEnum.getByType(skuOrderDO.getType()));
        skuOrderEntity.setStatusDesc(OrderStatusEnum.getByStatus(skuOrderDO.getStatus()));
        if (skuOrderDO.getConfirmTime() > 0) {
            skuOrderEntity.setConfirmTimeDate(new Date(skuOrderDO.getConfirmTime()));
        }
        return Optional.of(skuOrderEntity);
    }

    public static OrderItemDO toOrderItemDO(OrderItemEntity orderItemEntity) {
        OrderItemDO orderItemDO = new OrderItemDO();
        orderItemDO.setOrderId(orderItemEntity.getOrderId());
        orderItemDO.setSkuId(orderItemEntity.getSkuId());
        orderItemDO.setNumber(orderItemEntity.getNumber());
        return orderItemDO;
    }

    public static Optional<OrderItemEntity> toOrderItemEntity(OrderItemDO orderItemDO) {
        if (Objects.isNull(orderItemDO)) {
            return Optional.empty();
        }

        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setId(orderItemDO.getId());
        orderItemEntity.setOrderId(orderItemDO.getOrderId());
        orderItemEntity.setSkuId(orderItemDO.getSkuId());
        orderItemEntity.setNumber(orderItemDO.getNumber());
        orderItemEntity.setCreatedAt(orderItemDO.getCreatedAt());
        return Optional.of(orderItemEntity);
    }
}
