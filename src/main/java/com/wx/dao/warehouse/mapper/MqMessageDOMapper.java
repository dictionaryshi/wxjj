package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.MqMessageDO;
import com.wx.dao.warehouse.model.MqMessageDOExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface MqMessageDOMapper {
    @SelectProvider(type = MqMessageDOSqlProvider.class, method = "countByExample")
    long countByExample(MqMessageDOExample example);

    @DeleteProvider(type = MqMessageDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(MqMessageDOExample example);

    @Delete({
            "delete from mq_message",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = MqMessageDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(MqMessageDO row);

    @SelectProvider(type = MqMessageDOSqlProvider.class, method = "selectByExampleWithBLOBs")
    List<MqMessageDO> selectByExampleWithBLOBs(MqMessageDOExample example);

    @SelectProvider(type = MqMessageDOSqlProvider.class, method = "selectByExample")
    List<MqMessageDO> selectByExample(MqMessageDOExample example);

    @Select({
            "select",
            "id, topic, group, status, retry_count, sharding_id, timeout, effect_time, created_at, ",
            "updated_at, data, log",
            "from mq_message",
            "where id = #{id,jdbcType=BIGINT}"
    })
    MqMessageDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = MqMessageDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("row") MqMessageDO row, @Param("example") MqMessageDOExample example);

    @UpdateProvider(type = MqMessageDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MqMessageDO row);
}