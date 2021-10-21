package com.wx.controller.request.stock;

import com.scy.core.format.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author : shichunyang
 * Date    : 2021/2/1
 * Time    : 10:19 上午
 * ---------------------------------------
 * Desc    : 分页查询库存操作明细请求
 */
@ApiModel("分页查询库存操作明细请求")
@Getter
@Setter
@ToString
public class QueryStockDetailByPageRequest {

    /**
     * 仓库id
     */
    @NotNull(message = "仓库id 不为null")
    @ApiModelProperty(value = "仓库id", required = true, example = "123456")
    private Long stockBaseInfoId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", required = false, example = "桌子")
    private String skuName;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id", required = false, example = "123456")
    private String orderId;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", required = false, example = DateUtil.DEFAULT_TIME)
    private String startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", required = false, example = DateUtil.DEFAULT_TIME)
    private String endTime;
}
