package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.OrderItemDO;
import com.wx.dao.warehouse.model.OrderItemDOExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface OrderItemDOMapper {
    @SelectProvider(type = OrderItemDOSqlProvider.class, method = "countByExample")
    long countByExample(OrderItemDOExample example);

    @DeleteProvider(type = OrderItemDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(OrderItemDOExample example);

    @Delete({
            "delete from order_item",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = OrderItemDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(OrderItemDO record);

    @SelectProvider(type = OrderItemDOSqlProvider.class, method = "selectByExample")
    List<OrderItemDO> selectByExample(OrderItemDOExample example);

    @Select({
            "select",
            "id, order_id, sku_id, number, created_at, updated_at",
            "from order_item",
            "where id = #{id,jdbcType=BIGINT}"
    })
    OrderItemDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = OrderItemDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OrderItemDO record, @Param("example") OrderItemDOExample example);

    @UpdateProvider(type = OrderItemDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OrderItemDO record);
}