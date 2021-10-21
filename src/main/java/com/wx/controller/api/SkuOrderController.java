package com.wx.controller.api;

import com.scy.core.CollectionUtil;
import com.scy.core.model.DiffBO;
import com.scy.core.page.PageParam;
import com.scy.core.page.PageResult;
import com.scy.core.rest.ResponseResult;
import com.scy.redis.annotation.LimitAccessFrequency;
import com.scy.web.annotation.LoginCheck;
import com.wx.controller.assembler.SkuOrderAssembler;
import com.wx.controller.request.order.*;
import com.wx.controller.response.order.OrderItemResponse;
import com.wx.controller.response.order.SkuOrderResponse;
import com.wx.domain.order.entity.OrderItemEntity;
import com.wx.domain.order.entity.SkuOrderEntity;
import com.wx.service.SkuOrderFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 2:35 下午
 * ---------------------------------------
 * Desc    : 商品订单API
 */
@Api(tags = "商品订单API")
@Slf4j
@RequestMapping("/order")
@RestController
public class SkuOrderController {

    @Autowired
    private SkuOrderFacade skuOrderFacade;

    @ApiOperation("创建订单")
    @LimitAccessFrequency(redisKey = "createOrder", timeWindow = 10_000L, limit = 1)
    @LoginCheck
    @PostMapping("/create-order")
    public ResponseResult<Long> createOrder(
            @RequestBody @Valid CreateOrderRequest createOrderRequest
    ) {
        SkuOrderEntity skuOrderEntity = SkuOrderAssembler.toSkuOrderEntity(createOrderRequest);
        long orderId = skuOrderFacade.insertSkuOrder(skuOrderEntity);
        return ResponseResult.success(orderId);
    }

    @ApiOperation("查询订单")
    @LoginCheck
    @GetMapping("/get-order")
    public ResponseResult<SkuOrderResponse> getOrder(
            @Valid GetOrderRequest getOrderRequest
    ) {
        Optional<SkuOrderEntity> skuOrderEntityOptional = skuOrderFacade.getOrder(Long.parseLong(getOrderRequest.getOrderId()));
        return ResponseResult.success(skuOrderEntityOptional.map(SkuOrderAssembler::toSkuOrderResponse).orElse(null));
    }

    @ApiOperation("分页查询订单")
    @LoginCheck
    @GetMapping("/list-orders-by-page")
    public ResponseResult<PageResult<SkuOrderResponse>> listOrdersByPage(
            @Valid PageParam pageParam,
            @Valid QueryOrderByPageRequest queryOrderByPageRequest
    ) {
        PageResult<SkuOrderEntity> pageResult = skuOrderFacade.listByPage(pageParam, SkuOrderAssembler.toSkuOrderEntity(queryOrderByPageRequest));
        return ResponseResult.success(SkuOrderAssembler.toSkuOrderResponse(pageResult));
    }

    @ApiOperation("修改订单")
    @LimitAccessFrequency(redisKey = "updateOrder", timeWindow = 10_000L, limit = 1)
    @LoginCheck
    @PostMapping("/update-order")
    public ResponseResult<List<DiffBO>> updateOrder(
            @RequestBody @Valid UpdateOrderRequest updateOrderRequest
    ) {
        SkuOrderEntity skuOrderEntity = SkuOrderAssembler.toSkuOrderEntity(updateOrderRequest);
        List<DiffBO> diffs = skuOrderFacade.updateOrder(skuOrderEntity);
        return ResponseResult.success(diffs);
    }

    @ApiOperation("查询订单条目")
    @LoginCheck
    @GetMapping("/query-order-items")
    public ResponseResult<List<OrderItemResponse>> queryOrderItems(
            @Valid QueryOrderItemRequest queryOrderItemRequest
    ) {
        List<OrderItemEntity> orderItemEntities = skuOrderFacade.listOrderItemEntities(Long.parseLong(queryOrderItemRequest.getOrderId()));
        return ResponseResult.success(CollectionUtil.map(orderItemEntities, SkuOrderAssembler::toOrderItemResponse).collect(Collectors.toList()));
    }

    @ApiOperation("添加订单条目")
    @LimitAccessFrequency(redisKey = "addOrderItem", timeWindow = 10_000L, limit = 1)
    @LoginCheck
    @PostMapping("/add-order-item")
    public ResponseResult<Long> addOrderItem(
            @RequestBody @Valid AddOrderItemRequest addOrderItemRequest
    ) {
        OrderItemEntity orderItemEntity = SkuOrderAssembler.toOrderItemEntity(addOrderItemRequest);
        long id = skuOrderFacade.insertOrderItemEntity(orderItemEntity);
        return ResponseResult.success(id);
    }

    @ApiOperation("修改订单条目")
    @LoginCheck
    @PostMapping("/update-order-item")
    public ResponseResult<List<DiffBO>> updateOrderItem(
            @RequestBody @Valid UpdateOrderItemRequest updateOrderItemRequest
    ) {
        OrderItemEntity orderItemEntity = SkuOrderAssembler.toOrderItemEntity(updateOrderItemRequest);
        List<DiffBO> diffs = skuOrderFacade.updateOrderItemEntity(orderItemEntity);
        return ResponseResult.success(diffs);
    }

    @ApiOperation("删除订单条目")
    @LoginCheck
    @PostMapping("/delete-order-item")
    public ResponseResult<Boolean> deleteOrderItem(
            @RequestBody @Valid DeleteOrderItemRequest deleteOrderItemRequest
    ) {
        OrderItemEntity orderItemEntity = SkuOrderAssembler.toOrderItemEntity(deleteOrderItemRequest);
        boolean isDelete = skuOrderFacade.deleteOrderItemEntity(orderItemEntity);
        return ResponseResult.success(isDelete);
    }

    @ApiOperation("提交订单")
    @LimitAccessFrequency(redisKey = "confirmOrder", timeWindow = 10_000L, limit = 1)
    @LoginCheck
    @PostMapping("/confirm-order")
    public ResponseResult<Boolean> confirmOrder(
            @RequestBody @Valid ConfirmOrderRequest confirmOrderRequest
    ) {
        boolean flag = skuOrderFacade.confirmSkuOrder(Long.parseLong(confirmOrderRequest.getOrderId()));
        return ResponseResult.success(flag);
    }

    @ApiOperation("删除订单")
    @LoginCheck
    @PostMapping("/delete-order")
    public ResponseResult<Boolean> deleteOrder(
            @RequestBody @Valid DeleteOrderRequest deleteOrderRequest
    ) {
        boolean flag = skuOrderFacade.deleteOrder(Long.parseLong(deleteOrderRequest.getOrderId()));
        return ResponseResult.success(flag);
    }
}
