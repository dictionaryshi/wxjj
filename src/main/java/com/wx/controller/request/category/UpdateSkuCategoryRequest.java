package com.wx.controller.request.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2020/12/24
 * Time    : 4:11 下午
 * ---------------------------------------
 * Desc    : 修改品类
 */
@ApiModel("修改品类请求对象")
@Getter
@Setter
@ToString
public class UpdateSkuCategoryRequest {

    /**
     * 品类id
     */
    @NotNull(message = "categoryId 不为null")
    @ApiModelProperty(value = "品类唯一标识", required = true, example = "123456")
    private Long categoryId;

    /**
     * 品类名称
     */
    @NotBlank(message = "品类名称不能为空")
    @ApiModelProperty(value = "品类名称", required = true)
    private String categoryName;
}
