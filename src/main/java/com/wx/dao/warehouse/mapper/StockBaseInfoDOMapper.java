package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.StockBaseInfoDO;
import com.wx.dao.warehouse.model.StockBaseInfoDOExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface StockBaseInfoDOMapper {
    @SelectProvider(type = StockBaseInfoDOSqlProvider.class, method = "countByExample")
    long countByExample(StockBaseInfoDOExample example);

    @DeleteProvider(type = StockBaseInfoDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(StockBaseInfoDOExample example);

    @Delete({
            "delete from stock_base_info",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = StockBaseInfoDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(StockBaseInfoDO record);

    @SelectProvider(type = StockBaseInfoDOSqlProvider.class, method = "selectByExample")
    List<StockBaseInfoDO> selectByExample(StockBaseInfoDOExample example);

    @Select({
            "select",
            "id, name, address, created_at, updated_at",
            "from stock_base_info",
            "where id = #{id,jdbcType=BIGINT}"
    })
    StockBaseInfoDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = StockBaseInfoDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") StockBaseInfoDO record, @Param("example") StockBaseInfoDOExample example);

    @UpdateProvider(type = StockBaseInfoDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StockBaseInfoDO record);
}