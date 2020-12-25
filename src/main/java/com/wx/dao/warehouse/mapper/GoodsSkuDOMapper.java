package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.GoodsSkuDO;
import com.wx.dao.warehouse.model.GoodsSkuDOExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface GoodsSkuDOMapper {
    @SelectProvider(type = GoodsSkuDOSqlProvider.class, method = "countByExample")
    long countByExample(GoodsSkuDOExample example);

    @DeleteProvider(type = GoodsSkuDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(GoodsSkuDOExample example);

    @Delete({
            "delete from goods_sku",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = GoodsSkuDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(GoodsSkuDO record);

    @SelectProvider(type = GoodsSkuDOSqlProvider.class, method = "selectByExample")
    List<GoodsSkuDO> selectByExample(GoodsSkuDOExample example);

    @Select({
            "select",
            "id, sku_name, category_id, created_at, updated_at",
            "from goods_sku",
            "where id = #{id,jdbcType=BIGINT}"
    })
    GoodsSkuDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = GoodsSkuDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") GoodsSkuDO record, @Param("example") GoodsSkuDOExample example);

    @UpdateProvider(type = GoodsSkuDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(GoodsSkuDO record);
}