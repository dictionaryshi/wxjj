package com.wx.controller.api;

import com.scy.core.page.PageParam;
import com.scy.core.page.PageResult;
import com.scy.core.rest.ResponseResult;
import com.scy.redis.annotation.LimitAccessFrequency;
import com.scy.web.annotation.LoginCheck;
import com.wx.controller.assembler.SkuStockAssembler;
import com.wx.controller.request.stock.GetStockRequest;
import com.wx.controller.request.stock.QuerySkuStockByPageRequest;
import com.wx.controller.request.stock.QueryStockDetailByPageRequest;
import com.wx.controller.request.stock.UpdateStockRequest;
import com.wx.controller.response.stock.SkuStockDetailResponse;
import com.wx.controller.response.stock.SkuStockResponse;
import com.wx.controller.response.stock.StockChangeResponse;
import com.wx.domain.stock.entity.SkuStockDetailEntity;
import com.wx.domain.stock.entity.SkuStockEntity;
import com.wx.service.SkuStockFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author : shichunyang
 * Date    : 2021/1/30
 * Time    : 9:47 下午
 * ---------------------------------------
 * Desc    : 商品库存API
 */
@Api(tags = "商品库存API")
@Slf4j
@RequestMapping("/stock")
@RestController
public class SkuStockController {

    @Autowired
    private SkuStockFacade skuStockFacade;

    @ApiOperation("盘点库存")
    @LimitAccessFrequency(redisKey = "updateSkuStock", timeWindow = 10_000L, limit = 1)
    @LoginCheck
    @PostMapping("/update-sku-stock")
    public ResponseResult<StockChangeResponse> updateSkuStock(
            @RequestBody @Valid UpdateStockRequest updateStockRequest
    ) {
        SkuStockEntity skuStockEntity = SkuStockAssembler.toSkuStockEntity(updateStockRequest);
        skuStockEntity = skuStockFacade.updateStock(skuStockEntity);
        StockChangeResponse stockChangeResponse = SkuStockAssembler.toStockChangeResponse(skuStockEntity);
        return ResponseResult.success(stockChangeResponse);
    }

    @ApiOperation("查询商品库存")
    @LoginCheck
    @GetMapping("/get-sku-stock")
    public ResponseResult<SkuStockResponse> getSkuStock(
            @Valid GetStockRequest getStockRequest
    ) {
        Optional<SkuStockEntity> skuStockEntityOptional = skuStockFacade.getSkuStockEntity(getStockRequest.getStockBaseInfoId(), getStockRequest.getSkuId());
        return ResponseResult.success(SkuStockAssembler.toSkuStockResponse(skuStockEntityOptional.orElse(null)));
    }

    @ApiOperation("分页查询商品库存")
    @LoginCheck
    @GetMapping("/query-sku-stock-by-page")
    public ResponseResult<PageResult<SkuStockResponse>> querySkuStockByPage(
            @Valid PageParam pageParam,
            @Valid QuerySkuStockByPageRequest querySkuStockByPageRequest
    ) {
        PageResult<SkuStockEntity> pageResult = skuStockFacade.listByPage(pageParam, SkuStockAssembler.toSkuStockEntity(querySkuStockByPageRequest));
        return ResponseResult.success(SkuStockAssembler.toSkuStockResponse(pageResult));
    }

    @ApiOperation("分页查询库存操作明细")
    @LoginCheck
    @GetMapping("/query-stock-detail-by-page")
    public ResponseResult<PageResult<SkuStockDetailResponse>> queryStockDetailByPage(
            @Valid PageParam pageParam,
            @Valid QueryStockDetailByPageRequest queryStockDetailByPageRequest
    ) {
        PageResult<SkuStockDetailEntity> pageResult = skuStockFacade.listStockDetailByPage(pageParam, SkuStockAssembler.toSkuStockDetailEntity(queryStockDetailByPageRequest));
        return ResponseResult.success(SkuStockAssembler.toSkuStockDetailResponse(pageResult));
    }
}
