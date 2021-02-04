package com.wx.controller.request.order;

import com.scy.core.format.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 8:03 下午
 * ---------------------------------------
 * Desc    : 分页查询订单请求
 */
@ApiModel("分页查询订单请求")
@Getter
@Setter
@ToString
public class QueryOrderByPageRequest {

    /**
     * 订单类型
     */
    @NotNull(message = "type 不为null")
    @ApiModelProperty(value = "订单类型(1:入库, 2:出库)", required = true, example = "1")
    private Integer type;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id", required = false, example = "123456")
    private Long orderId;

    /**
     * 仓库id
     */
    @ApiModelProperty(value = "仓库id", required = false, example = "123456")
    private Long stockBaseInfoId;

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

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态(0:未提交, 1:已提交)", required = false, example = "1")
    private Integer status;

    /**
     * 客户电话
     */
    @ApiModelProperty(value = "客户电话", required = false, example = "13264232894")
    private String customerPhone;
}
