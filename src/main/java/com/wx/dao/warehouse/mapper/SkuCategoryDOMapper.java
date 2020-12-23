package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.SkuCategoryDO;
import com.wx.dao.warehouse.model.SkuCategoryDOExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface SkuCategoryDOMapper {
    @SelectProvider(type = SkuCategoryDOSqlProvider.class, method = "countByExample")
    long countByExample(SkuCategoryDOExample example);

    @DeleteProvider(type = SkuCategoryDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(SkuCategoryDOExample example);

    @Delete({
            "delete from sku_category",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = SkuCategoryDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(SkuCategoryDO record);

    @SelectProvider(type = SkuCategoryDOSqlProvider.class, method = "selectByExample")
    List<SkuCategoryDO> selectByExample(SkuCategoryDOExample example);

    @Select({
            "select",
            "id, category_name, created_at, updated_at",
            "from sku_category",
            "where id = #{id,jdbcType=BIGINT}"
    })
    SkuCategoryDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = SkuCategoryDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SkuCategoryDO record, @Param("example") SkuCategoryDOExample example);

    @UpdateProvider(type = SkuCategoryDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SkuCategoryDO record);
}