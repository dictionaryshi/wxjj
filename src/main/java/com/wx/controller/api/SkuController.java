package com.wx.controller.api;

import com.scy.core.rest.ResponseResult;
import com.scy.web.annotation.LoginCheck;
import com.wx.controller.assembler.GoodsSkuAssembler;
import com.wx.controller.request.goods.AddGoodsSkuRequest;
import com.wx.domain.sku.entity.GoodsSkuEntity;
import com.wx.service.GoodsSkuFacade;
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
}
