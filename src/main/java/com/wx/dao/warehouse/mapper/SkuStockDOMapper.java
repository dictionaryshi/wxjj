package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.SkuStockDO;
import com.wx.dao.warehouse.model.SkuStockDOExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface SkuStockDOMapper {
    @SelectProvider(type = SkuStockDOSqlProvider.class, method = "countByExample")
    long countByExample(SkuStockDOExample example);

    @DeleteProvider(type = SkuStockDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(SkuStockDOExample example);

    @Delete({
            "delete from sku_stock",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = SkuStockDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(SkuStockDO record);

    @SelectProvider(type = SkuStockDOSqlProvider.class, method = "selectByExample")
    List<SkuStockDO> selectByExample(SkuStockDOExample example);

    @Select({
            "select",
            "id, stock_base_info_id, sku_id, stock, created_at, updated_at",
            "from sku_stock",
            "where id = #{id,jdbcType=BIGINT}"
    })
    SkuStockDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = SkuStockDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SkuStockDO record, @Param("example") SkuStockDOExample example);

    @UpdateProvider(type = SkuStockDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SkuStockDO record);
}