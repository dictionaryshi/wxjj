package com.wx.controller.api;

import com.scy.core.rest.ResponseResult;
import com.scy.web.annotation.LoginCheck;
import com.wx.controller.assembler.SkuCategoryAssembler;
import com.wx.controller.request.category.AddSkuCategoryRequest;
import com.wx.controller.request.category.GetSkuCategoryRequest;
import com.wx.controller.response.category.SkuCategoryResponse;
import com.wx.domain.category.entity.SkuCategoryEntity;
import com.wx.service.SkuCategoryFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : shichunyang
 * Date    : 2020/12/24
 * Time    : 11:39 上午
 * ---------------------------------------
 * Desc    : 品类用户接口
 */
@Api(tags = "品类")
@Slf4j
@RequestMapping("/category")
@RestController
public class SkuCategoryController {

    @Autowired
    private SkuCategoryFacade skuCategoryFacade;

    @ApiOperation("添加品类")
    @LoginCheck
    @PostMapping("/add-category")
    public ResponseResult<Long> addCategory(
            @RequestBody @Valid AddSkuCategoryRequest addSkuCategoryRequest
    ) {
        long categoryId = skuCategoryFacade.insertSkuCategory(addSkuCategoryRequest.getCategoryName());
        return ResponseResult.success(categoryId);
    }

    @ApiOperation("查询品类")
    @LoginCheck
    @GetMapping("/get-category-by-id")
    public ResponseResult<SkuCategoryResponse> getCategoryById(
            @Valid GetSkuCategoryRequest getSkuCategoryRequest
    ) {
        SkuCategoryEntity skuCategoryEntity = skuCategoryFacade.getSkuCategoryEntity(getSkuCategoryRequest.getCategoryId());
        SkuCategoryResponse skuCategoryResponse = SkuCategoryAssembler.toSkuCategoryResponse(skuCategoryEntity);
        return ResponseResult.success(skuCategoryResponse);
    }
}
