package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.SkuOrderDO;
import com.wx.dao.warehouse.model.SkuOrderDOExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface SkuOrderDOMapper {
    @SelectProvider(type = SkuOrderDOSqlProvider.class, method = "countByExample")
    long countByExample(SkuOrderDOExample example);

    @DeleteProvider(type = SkuOrderDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(SkuOrderDOExample example);

    @Delete({
            "delete from sku_order",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = SkuOrderDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(SkuOrderDO record);

    @SelectProvider(type = SkuOrderDOSqlProvider.class, method = "selectByExample")
    List<SkuOrderDO> selectByExample(SkuOrderDOExample example);

    @Select({
            "select",
            "id, order_id, type, status, confirm_time, operator, price, customer_name, customer_phone, ",
            "customer_address, remark, created_at, updated_at",
            "from sku_order",
            "where id = #{id,jdbcType=BIGINT}"
    })
    SkuOrderDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = SkuOrderDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SkuOrderDO record, @Param("example") SkuOrderDOExample example);

    @UpdateProvider(type = SkuOrderDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SkuOrderDO record);
}