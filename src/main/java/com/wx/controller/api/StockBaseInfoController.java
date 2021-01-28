package com.wx.controller.api;

import com.scy.core.rest.ResponseResult;
import com.scy.web.annotation.LoginCheck;
import com.wx.controller.assembler.StockBaseInfoAssembler;
import com.wx.controller.request.stock.AddStockBaseInfoRequest;
import com.wx.domain.stock.entity.StockBaseInfoEntity;
import com.wx.service.StockBaseInfoFacade;
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
 * Date    : 2021/1/28
 * Time    : 11:27 上午
 * ---------------------------------------
 * Desc    : 仓库基本信息API
 */
@Api(tags = "仓库基本信息API")
@Slf4j
@RequestMapping("/stock-base")
@RestController
public class StockBaseInfoController {

    @Autowired
    private StockBaseInfoFacade stockBaseInfoFacade;

    @ApiOperation("添加仓库基本信息")
    @LoginCheck
    @PostMapping("/add-stock-base-info")
    public ResponseResult<Long> addStockBaseInfo(
            @RequestBody @Valid AddStockBaseInfoRequest addStockBaseInfoRequest
    ) {
        StockBaseInfoEntity stockBaseInfoEntity = StockBaseInfoAssembler.toStockBaseInfoEntity(addStockBaseInfoRequest);
        long id = stockBaseInfoFacade.insert(stockBaseInfoEntity);
        return ResponseResult.success(id);
    }
}
