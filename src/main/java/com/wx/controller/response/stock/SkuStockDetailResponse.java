package com.wx.controller.response.stock;

import com.scy.core.format.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author : shichunyang
 * Date    : 2021/2/1
 * Time    : 10:43 上午
 * ---------------------------------------
 * Desc    : 库存操作明细响应
 */
@ApiModel("库存操作明细响应")
@Getter
@Setter
@ToString
public class SkuStockDetailResponse {

    /**
     * 库存操作值
     */
    @ApiModelProperty(value = "库存操作值", required = true, example = "50")
    private Long stockOffset;

    /**
     * 操作前库存
     */
    @ApiModelProperty(value = "操作前库存", required = true, example = "0")
    private Long stockBefore;

    /**
     * 操作后库存
     */
    @ApiModelProperty(value = "操作后库存", required = true, example = "50")
    private Long stockAfter;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id", required = true, example = "123456")
    private Long orderId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true, example = DateUtil.DEFAULT_TIME)
    private Date createdAt;


    /**
     * 仓库名称
     */
    @ApiModelProperty(value = "仓库名称", required = false, example = "河南仓库")
    private String stockBaseInfoName;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", required = false, example = "桌子")
    private String skuName;

    /**
     * 操作类型描述
     */
    @ApiModelProperty(value = "操作类型描述", required = false, example = "出库/入库")
    private String typeDesc;

    /**
     * 操作人姓名
     */
    @ApiModelProperty(value = "操作人姓名", required = false, example = "13264232894")
    private String operatorName;
}
