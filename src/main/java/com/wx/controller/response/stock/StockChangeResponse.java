package com.wx.controller.response.stock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2021/1/30
 * Time    : 9:31 下午
 * ---------------------------------------
 * Desc    : 库存变化响应
 */
@ApiModel("库存变化响应")
@Getter
@Setter
@ToString
public class StockChangeResponse {

    /**
     * 库存操作值
     */
    @ApiModelProperty(value = "库存操作值", required = true, example = "50")
    private Long stockOffset;

    /**
     * 操作前库存
     */
    @ApiModelProperty(value = "操作前库存", required = true, example = "100")
    private Long stockBefore;

    /**
     * 操作后库存
     */
    @ApiModelProperty(value = "操作后库存", required = true, example = "150")
    private Long stockAfter;
}
