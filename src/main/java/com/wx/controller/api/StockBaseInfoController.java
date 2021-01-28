package com.wx.controller.api;

import com.scy.core.CollectionUtil;
import com.scy.core.rest.ResponseResult;
import com.scy.web.annotation.LoginCheck;
import com.wx.controller.assembler.StockBaseInfoAssembler;
import com.wx.controller.request.stock.AddStockBaseInfoRequest;
import com.wx.controller.request.stock.GetStockBaseInfoRequest;
import com.wx.controller.response.stock.StockBaseInfoResponse;
import com.wx.domain.stock.entity.StockBaseInfoEntity;
import com.wx.service.StockBaseInfoFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @ApiOperation("查询仓库基本信息")
    @LoginCheck
    @GetMapping("/get-stock-base-info-by-id")
    public ResponseResult<StockBaseInfoResponse> getStockBaseInfoById(
            @Valid GetStockBaseInfoRequest getStockBaseInfoRequest
    ) {
        Optional<StockBaseInfoEntity> stockBaseInfoEntityOptional = stockBaseInfoFacade.get(getStockBaseInfoRequest.getId());
        return ResponseResult.success(stockBaseInfoEntityOptional.flatMap(StockBaseInfoAssembler::toStockBaseInfoResponse).orElse(null));
    }

    @ApiOperation("查询所有仓库基本信息")
    @LoginCheck
    @GetMapping("/list-all-stock-base-infos")
    public ResponseResult<List<StockBaseInfoResponse>> listAllStockBaseInfos() {
        List<StockBaseInfoEntity> stockBaseInfoEntities = stockBaseInfoFacade.listAll();
        List<StockBaseInfoResponse> stockBaseInfoResponses = CollectionUtil.emptyIfNull(stockBaseInfoEntities).stream()
                .map(StockBaseInfoAssembler::toStockBaseInfoResponse)
                .map(optional -> optional.orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return ResponseResult.success(stockBaseInfoResponses);
    }
}
