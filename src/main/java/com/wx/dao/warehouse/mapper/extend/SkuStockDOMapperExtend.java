package com.wx.dao.warehouse.mapper.extend;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author : shichunyang
 * Date    : 2021/1/28
 * Time    : 8:54 下午
 * ---------------------------------------
 * Desc    : SkuStockDOMapper扩展
 */
public interface SkuStockDOMapperExtend {

    @Update("update `sku_stock` set `stock` = `stock` + #{stockOffset} where `id` = #{id}")
    int addStock(@Param("id") long id, @Param("stockOffset") long stockOffset);

    @Update("update `sku_stock` set `stock` = `stock` - #{stockOffset} where `id` = #{id}")
    int reduceStock(@Param("id") long id, @Param("stockOffset") long stockOffset);
}
