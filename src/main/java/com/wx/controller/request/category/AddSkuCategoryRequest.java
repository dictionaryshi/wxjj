package com.wx.controller.request.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author : shichunyang
 * Date    : 2020/12/24
 * Time    : 11:36 上午
 * ---------------------------------------
 * Desc    : 添加品类
 */
@ApiModel("添加品类请求对象")
@Getter
@Setter
@ToString
public class AddSkuCategoryRequest {

    @NotBlank(message = "品类名称不能为空")
    @ApiModelProperty(value = "品类名称", required = true)
    private String categoryName;
}
