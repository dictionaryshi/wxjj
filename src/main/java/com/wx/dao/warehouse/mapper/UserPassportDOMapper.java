package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.UserPassportDO;
import com.wx.dao.warehouse.model.UserPassportDOExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface UserPassportDOMapper {
    @SelectProvider(type = UserPassportDOSqlProvider.class, method = "countByExample")
    long countByExample(UserPassportDOExample example);

    @DeleteProvider(type = UserPassportDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(UserPassportDOExample example);

    @Delete({
            "delete from user_passport",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = UserPassportDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(UserPassportDO record);

    @SelectProvider(type = UserPassportDOSqlProvider.class, method = "selectByExample")
    List<UserPassportDO> selectByExample(UserPassportDOExample example);

    @Select({
            "select",
            "id, passport, password, name, created_at, updated_at",
            "from user_passport",
            "where id = #{id,jdbcType=BIGINT}"
    })
    UserPassportDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = UserPassportDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserPassportDO record, @Param("example") UserPassportDOExample example);

    @UpdateProvider(type = UserPassportDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserPassportDO record);
}