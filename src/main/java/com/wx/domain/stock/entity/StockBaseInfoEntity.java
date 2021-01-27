package com.wx.domain.stock.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2021/1/27
 * Time    : 1:27 下午
 * ---------------------------------------
 * Desc    : 仓库基本信息实体
 */
@Getter
@Setter
@ToString
public class StockBaseInfoEntity {

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;
}
