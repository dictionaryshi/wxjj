package com.wx.dao.warehouse.mapper.extend;

import com.google.common.collect.Lists;
import com.scy.core.db.SqlUtil;
import com.wx.dao.warehouse.model.SkuStockDetailDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.PrimitiveIterator;

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


    @InsertProvider(type = A.class, method = "batchInsert")
    int batchInsert(List<SkuStockDetailDO> skuStockDetails);
}
