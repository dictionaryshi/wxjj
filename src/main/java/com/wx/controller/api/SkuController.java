package com.wx.controller.api;

import com.scy.core.CollectionUtil;
import com.scy.core.model.DiffBO;
import com.scy.core.page.PageParam;
import com.scy.core.page.PageResult;
import com.scy.core.rest.ResponseResult;
import com.scy.web.annotation.LoginCheck;
import com.wx.controller.assembler.GoodsSkuAssembler;
import com.wx.controller.request.goods.*;
import com.wx.controller.response.goods.GoodsSkuResponse;
import com.wx.domain.sku.entity.GoodsSkuEntity;
import com.wx.service.GoodsSkuFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2020/12/25
 * Time    : 11:47 上午
 * ---------------------------------------
 * Desc    : 商品API
 */
@Api(tags = "商品API")
@Slf4j
@RequestMapping("/sku")
@RestController
public class SkuController {

    @Autowired
    private GoodsSkuFacade goodsSkuFacade;

    @ApiOperation("添加商品")
    @LoginCheck
    @PostMapping("/add-sku")
    public ResponseResult<Long> addSku(
            @RequestBody @Valid AddGoodsSkuRequest addGoodsSkuRequest
    ) {
        GoodsSkuEntity goodsSkuEntity = GoodsSkuAssembler.toGoodsSkuEntity(addGoodsSkuRequest);
        long skuId = goodsSkuFacade.add(goodsSkuEntity);
        return ResponseResult.success(skuId);
    }

    @ApiOperation("修改商品")
    @LoginCheck
    @PostMapping("/update-sku")
    public ResponseResult<List<DiffBO>> updateSku(
            @RequestBody @Valid UpdateGoodsSkuRequest updateGoodsSkuRequest
    ) {
        GoodsSkuEntity goodsSkuEntity = GoodsSkuAssembler.toGoodsSkuEntity(updateGoodsSkuRequest);
        List<DiffBO> diffs = goodsSkuFacade.update(goodsSkuEntity);
        return ResponseResult.success(diffs);
    }

    @ApiOperation("查询商品")
    @LoginCheck
    @GetMapping("/get-sku")
    public ResponseResult<GoodsSkuResponse> getSku(
            @Valid GetGoodsSkuRequest getGoodsSkuRequest
    ) {
        GoodsSkuEntity goodsSkuEntity = goodsSkuFacade.getGoodsSkuEntity(getGoodsSkuRequest.getSkuId());
        return ResponseResult.success(GoodsSkuAssembler.toGoodsSkuResponse(goodsSkuEntity));
    }

    @ApiOperation("根据分类查询所有商品")
    @LoginCheck
    @GetMapping("/query-sku-by-category")
    public ResponseResult<List<GoodsSkuResponse>> querySkuByCategory(
            @Valid QueryGoodsSkuByCategoryRequest queryGoodsSkuByCategoryRequest
    ) {
        List<GoodsSkuEntity> goodsSkuEntities = goodsSkuFacade.listByCategoryId(queryGoodsSkuByCategoryRequest.getCategoryId());
        List<GoodsSkuResponse> goodsSkuResponses = CollectionUtil.map(goodsSkuEntities, GoodsSkuAssembler::toGoodsSkuResponse).collect(Collectors.toList());
        return ResponseResult.success(goodsSkuResponses);
    }

    @ApiOperation("分页查询sku")
    @LoginCheck
    @GetMapping("/query-sku-by-page")
    public ResponseResult<PageResult<GoodsSkuResponse>> querySkuByPage(
            @Valid PageParam pageParam,
            QueryGoodsSkuByPageRequest queryGoodsSkuByPageRequest
    ) {
        GoodsSkuEntity goodsSkuEntity = GoodsSkuAssembler.toGoodsSkuEntity(queryGoodsSkuByPageRequest);
        PageResult<GoodsSkuEntity> pageResult = goodsSkuFacade.listByPage(pageParam, goodsSkuEntity);
        return ResponseResult.success(GoodsSkuAssembler.toGoodsSkuResponsePageResult(pageResult));
    }
}
