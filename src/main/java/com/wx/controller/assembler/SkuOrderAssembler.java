package com.wx.controller.assembler;

import com.scy.core.StringUtil;
import com.scy.core.format.DateUtil;
import com.scy.core.format.NumberUtil;
import com.scy.core.page.PageResult;
import com.scy.web.util.LoginUtil;
import com.wx.controller.request.order.CreateOrderRequest;
import com.wx.controller.request.order.QueryOrderByPageRequest;
import com.wx.controller.request.order.UpdateOrderRequest;
import com.wx.controller.response.order.SkuOrderResponse;
import com.wx.domain.order.entity.SkuOrderEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
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
        skuOrderResponse.setOrderId(skuOrderEntity.getOrderId());
        skuOrderResponse.setPrice(NumberUtil.fenToYuan(skuOrderEntity.getPrice()));
        skuOrderResponse.setCustomerName(skuOrderEntity.getCustomerName());
        skuOrderResponse.setCustomerPhone(skuOrderEntity.getCustomerPhone());
        skuOrderResponse.setCustomerAddress(skuOrderEntity.getCustomerAddress());
        skuOrderResponse.setRemark(skuOrderEntity.getRemark());
        skuOrderResponse.setCreatedAt(skuOrderEntity.getCreatedAt());
        skuOrderResponse.setTypeDesc(skuOrderEntity.getTypeDesc());
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
        skuOrderEntity.setOrderId(queryOrderByPageRequest.getOrderId());
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
        skuOrderEntity.setOrderId(updateOrderRequest.getOrderId());
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
}
