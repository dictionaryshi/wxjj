package com.wx.controller.request.stock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2021/1/28
 * Time    : 2:09 下午
 * ---------------------------------------
 * Desc    : 修改仓库基本信息请求
 */
@ApiModel("修改仓库基本信息请求")
@Getter
@Setter
@ToString
public class UpdateStockBaseInfoRequest {

    @NotNull(message = "id 不能为null")
    @ApiModelProperty(value = "id", required = true, example = "123456")
    private Long id;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称", required = true, example = "鹤岗仓库")
    private String name;

    @ApiModelProperty(value = "地址", example = "黑龙江省鹤岗市")
    private String address;
}
