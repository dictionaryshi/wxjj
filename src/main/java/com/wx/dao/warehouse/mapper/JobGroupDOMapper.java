package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.JobGroupDO;
import com.wx.dao.warehouse.model.JobGroupDOExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface JobGroupDOMapper {
    @SelectProvider(type = JobGroupDOSqlProvider.class, method = "countByExample")
    long countByExample(JobGroupDOExample example);

    @DeleteProvider(type = JobGroupDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(JobGroupDOExample example);

    @Delete({
            "delete from job_group",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = JobGroupDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(JobGroupDO row);

    @SelectProvider(type = JobGroupDOSqlProvider.class, method = "selectByExampleWithBLOBs")
    List<JobGroupDO> selectByExampleWithBLOBs(JobGroupDOExample example);

    @SelectProvider(type = JobGroupDOSqlProvider.class, method = "selectByExample")
    List<JobGroupDO> selectByExample(JobGroupDOExample example);

    @Select({
            "select",
            "id, app_name, name, address_type, created_at, updated_at, address_list",
            "from job_group",
            "where id = #{id,jdbcType=BIGINT}"
    })
    JobGroupDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = JobGroupDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("row") JobGroupDO row, @Param("example") JobGroupDOExample example);

    @UpdateProvider(type = JobGroupDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(JobGroupDO row);
}