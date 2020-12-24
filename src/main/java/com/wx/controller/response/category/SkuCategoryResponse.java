package com.wx.controller.response.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2020/12/24
 * Time    : 1:06 下午
 * ---------------------------------------
 * Desc    : 商品分类响应对象
 */
@ApiModel(value = "商品分类响应对象")
@Setter
@Getter
@ToString
public class SkuCategoryResponse {

    /**
     * 品类id
     */
    @ApiModelProperty(value = "品类id", required = true, example = "123456")
    private Long categoryId;

    /**
     * 品类名称
     */
    @ApiModelProperty(value = "品类名称", required = true, example = "桌子")
    private String categoryName;
}
