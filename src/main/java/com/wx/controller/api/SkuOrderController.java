package com.wx.controller.api;

import com.scy.core.rest.ResponseResult;
import com.scy.redis.annotation.LimitAccessFrequency;
import com.scy.web.annotation.LoginCheck;
import com.wx.controller.assembler.SkuOrderAssembler;
import com.wx.controller.request.order.CreateOrderRequest;
import com.wx.controller.request.order.GetOrderRequest;
import com.wx.controller.response.order.SkuOrderResponse;
import com.wx.domain.order.entity.SkuOrderEntity;
import com.wx.service.SkuOrderFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
            @RequestBody @Valid GetOrderRequest getOrderRequest
    ) {
        Optional<SkuOrderEntity> skuOrderEntityOptional = skuOrderFacade.getOrder(getOrderRequest.getOrderId());
        return ResponseResult.success(skuOrderEntityOptional.map(SkuOrderAssembler::toSkuOrderResponse).orElse(null));
    }
}
