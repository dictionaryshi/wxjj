package com.wx.controller.api;

import com.scy.core.rest.ResponseResult;
import com.scy.web.annotation.LoginCheck;
import com.wx.controller.assembler.SkuStockAssembler;
import com.wx.controller.request.stock.UpdateStockRequest;
import com.wx.controller.response.stock.StockChangeResponse;
import com.wx.domain.stock.entity.SkuStockEntity;
import com.wx.service.SkuStockFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
