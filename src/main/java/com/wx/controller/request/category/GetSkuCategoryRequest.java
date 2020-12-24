package com.wx.controller.request.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2020/12/24
 * Time    : 1:02 下午
 * ---------------------------------------
 * Desc    : 查询品类
 */
@ApiModel("查询品类请求对象")
@Getter
@Setter
@ToString
public class GetSkuCategoryRequest {

    /**
     * 品类id
     */
    @NotNull(message = "categoryId 不为null")
    @ApiModelProperty(value = "品类唯一标识", required = true, example = "123456")
    private Long categoryId;
}
