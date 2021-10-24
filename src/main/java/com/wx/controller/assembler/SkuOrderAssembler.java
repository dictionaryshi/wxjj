package com.wx.controller.assembler;

import com.scy.core.StringUtil;
import com.scy.core.format.DateUtil;
import com.scy.core.format.NumberUtil;
import com.scy.core.page.PageResult;
import com.scy.web.util.LoginUtil;
import com.wx.controller.request.order.*;
import com.wx.controller.response.order.OrderItemResponse;
import com.wx.controller.response.order.SkuOrderResponse;
import com.wx.domain.order.entity.OrderItemEntity;
import com.wx.domain.order.entity.SkuOrderEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        skuOrderEntity.setStockBaseInfoId(createOrderRequest.getStockBaseInfoId());
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

    public static SkuOrderResponse toSkuOrderResponse(SkuOrderEntity skuOrderEntity) {
        SkuOrderResponse skuOrderResponse = new SkuOrderResponse();
        skuOrderResponse.setOrderId(Objects.isNull(skuOrderEntity.getOrderId()) ? StringUtil.EMPTY : String.valueOf(skuOrderEntity.getOrderId()));
        skuOrderResponse.setPrice(NumberUtil.fenToYuan(skuOrderEntity.getPrice()));
        skuOrderResponse.setCustomerName(skuOrderEntity.getCustomerName());
        skuOrderResponse.setCustomerPhone(skuOrderEntity.getCustomerPhone());
        skuOrderResponse.setCustomerAddress(skuOrderEntity.getCustomerAddress());
        skuOrderResponse.setRemark(skuOrderEntity.getRemark());
        skuOrderResponse.setCreatedAt(skuOrderEntity.getCreatedAt());
        skuOrderResponse.setStockBaseInfoId(skuOrderEntity.getStockBaseInfoId());
        skuOrderResponse.setStockBaseInfoName(skuOrderEntity.getStockBaseInfoName());
        skuOrderResponse.setType(skuOrderEntity.getType());
        skuOrderResponse.setTypeDesc(skuOrderEntity.getTypeDesc());
        skuOrderResponse.setStatus(skuOrderEntity.getStatus());
        skuOrderResponse.setStatusDesc(skuOrderEntity.getStatusDesc());
        skuOrderResponse.setConfirmTimeDate(skuOrderEntity.getConfirmTimeDate());
        skuOrderResponse.setOperatorName(skuOrderEntity.getOperatorName());
        return skuOrderResponse;
    }

    public static PageResult<SkuOrderResponse> toSkuOrderResponse(PageResult<SkuOrderEntity> skuOrderEntityPageResult) {
        PageResult<SkuOrderResponse> pageResult = new PageResult<>();
        pageResult.setPage(skuOrderEntityPageResult.getPage());
        pageResult.setLimit(skuOrderEntityPageResult.getLimit());
        pageResult.setTotal(skuOrderEntityPageResult.getTotal());
        pageResult.setMaxPage(skuOrderEntityPageResult.getMaxPage());
        pageResult.setDatas(skuOrderEntityPageResult.getDatas().stream().map(SkuOrderAssembler::toSkuOrderResponse).collect(Collectors.toList()));
        return pageResult;
    }

    public static SkuOrderEntity toSkuOrderEntity(QueryOrderByPageRequest queryOrderByPageRequest) {
        SkuOrderEntity skuOrderEntity = new SkuOrderEntity();
        skuOrderEntity.setOrderId(StringUtil.isEmpty(queryOrderByPageRequest.getOrderId()) ? null : Long.parseLong(queryOrderByPageRequest.getOrderId()));
        skuOrderEntity.setStockBaseInfoId(queryOrderByPageRequest.getStockBaseInfoId());
        skuOrderEntity.setType(queryOrderByPageRequest.getType());
        skuOrderEntity.setStatus(queryOrderByPageRequest.getStatus());
        skuOrderEntity.setCustomerPhone(queryOrderByPageRequest.getCustomerPhone());
        if (!StringUtil.isEmpty(queryOrderByPageRequest.getStartTime())) {
            skuOrderEntity.setCreatedAtStart(DateUtil.str2Date(queryOrderByPageRequest.getStartTime(), DateUtil.PATTERN_SECOND));
        }
        if (!StringUtil.isEmpty(queryOrderByPageRequest.getEndTime())) {
            skuOrderEntity.setCreatedAtEnd(DateUtil.str2Date(queryOrderByPageRequest.getEndTime(), DateUtil.PATTERN_SECOND));
        }
        return skuOrderEntity;
    }

    public static SkuOrderEntity toSkuOrderEntity(UpdateOrderRequest updateOrderRequest) {
        SkuOrderEntity skuOrderEntity = new SkuOrderEntity();
        skuOrderEntity.setOrderId(Long.parseLong(updateOrderRequest.getOrderId()));
        skuOrderEntity.setStockBaseInfoId(updateOrderRequest.getStockBaseInfoId());
        skuOrderEntity.setOperator(LoginUtil.getLoginUser().getUserId());
        if (!Objects.isNull(updateOrderRequest.getPrice())) {
            skuOrderEntity.setPrice(NumberUtil.yuanToFen(updateOrderRequest.getPrice()));
        }
        skuOrderEntity.setCustomerName(updateOrderRequest.getCustomerName());
        skuOrderEntity.setCustomerPhone(updateOrderRequest.getCustomerPhone());
        skuOrderEntity.setCustomerAddress(updateOrderRequest.getCustomerAddress());
        skuOrderEntity.setRemark(updateOrderRequest.getRemark());
        return skuOrderEntity;
    }

    public static OrderItemResponse toOrderItemResponse(OrderItemEntity orderItemEntity, SkuOrderEntity skuOrderEntity) {
        return Optional.ofNullable(orderItemEntity).map(order -> {
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.setOrderItemId(orderItemEntity.getId());
            orderItemResponse.setOrderId(Objects.isNull(orderItemEntity.getOrderId()) ? StringUtil.EMPTY : String.valueOf(orderItemEntity.getOrderId()));
            orderItemResponse.setSkuId(orderItemEntity.getSkuId());
            orderItemResponse.setNumber(orderItemEntity.getNumber());
            orderItemResponse.setCreatedAt(orderItemEntity.getCreatedAt());
            orderItemResponse.setSkuName(orderItemEntity.getSkuName());
            Optional.ofNullable(skuOrderEntity).ifPresent(skuOrder -> {
                orderItemResponse.setStatus(skuOrder.getStatus());
                orderItemResponse.setStatusDesc(skuOrder.getStatusDesc());
            });
            return orderItemResponse;
        }).orElse(null);
    }

    public static OrderItemEntity toOrderItemEntity(AddOrderItemRequest addOrderItemRequest) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrderId(Long.parseLong(addOrderItemRequest.getOrderId()));
        orderItemEntity.setSkuId(addOrderItemRequest.getSkuId());
        orderItemEntity.setNumber(addOrderItemRequest.getNumber());
        return orderItemEntity;
    }

    public static OrderItemEntity toOrderItemEntity(UpdateOrderItemRequest updateOrderItemRequest) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrderId(Long.parseLong(updateOrderItemRequest.getOrderId()));
        orderItemEntity.setSkuId(updateOrderItemRequest.getSkuId());
        orderItemEntity.setNumber(updateOrderItemRequest.getNumber());
        return orderItemEntity;
    }

    public static OrderItemEntity toOrderItemEntity(DeleteOrderItemRequest deleteOrderItemRequest) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrderId(Long.parseLong(deleteOrderItemRequest.getOrderId()));
        orderItemEntity.setSkuId(deleteOrderItemRequest.getSkuId());
        return orderItemEntity;
    }
}
