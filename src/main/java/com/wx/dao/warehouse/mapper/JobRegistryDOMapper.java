package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.JobRegistryDO;
import com.wx.dao.warehouse.model.JobRegistryDOExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface JobRegistryDOMapper {
    @SelectProvider(type = JobRegistryDOSqlProvider.class, method = "countByExample")
    long countByExample(JobRegistryDOExample example);

    @DeleteProvider(type = JobRegistryDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(JobRegistryDOExample example);

    @Delete({
            "delete from job_registry",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = JobRegistryDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(JobRegistryDO row);

    @SelectProvider(type = JobRegistryDOSqlProvider.class, method = "selectByExample")
    List<JobRegistryDO> selectByExample(JobRegistryDOExample example);

    @Select({
            "select",
            "id, app_name, address, created_at, updated_at",
            "from job_registry",
            "where id = #{id,jdbcType=BIGINT}"
    })
    JobRegistryDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = JobRegistryDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("row") JobRegistryDO row, @Param("example") JobRegistryDOExample example);

    @UpdateProvider(type = JobRegistryDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(JobRegistryDO row);
}