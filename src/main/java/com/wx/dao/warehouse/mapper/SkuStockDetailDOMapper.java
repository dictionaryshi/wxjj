package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.SkuStockDetailDO;
import com.wx.dao.warehouse.model.SkuStockDetailDOExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface SkuStockDetailDOMapper {
    @SelectProvider(type = SkuStockDetailDOSqlProvider.class, method = "countByExample")
    long countByExample(SkuStockDetailDOExample example);

    @DeleteProvider(type = SkuStockDetailDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(SkuStockDetailDOExample example);

    @Delete({
            "delete from sku_stock_detail",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = SkuStockDetailDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(SkuStockDetailDO record);

    @SelectProvider(type = SkuStockDetailDOSqlProvider.class, method = "selectByExample")
    List<SkuStockDetailDO> selectByExample(SkuStockDetailDOExample example);

    @Select({
            "select",
            "id, stock_base_info_id, sku_id, type, stock_offset, stock_before, stock_after, ",
            "order_id, operator, remark, created_at, updated_at",
            "from sku_stock_detail",
            "where id = #{id,jdbcType=BIGINT}"
    })
    SkuStockDetailDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = SkuStockDetailDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SkuStockDetailDO record, @Param("example") SkuStockDetailDOExample example);

    @UpdateProvider(type = SkuStockDetailDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SkuStockDetailDO record);
}