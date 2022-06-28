package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.JobLogDO;
import com.wx.dao.warehouse.model.JobLogDOExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface JobLogDOMapper {
    @SelectProvider(type = JobLogDOSqlProvider.class, method = "countByExample")
    long countByExample(JobLogDOExample example);

    @DeleteProvider(type = JobLogDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(JobLogDOExample example);

    @Delete({
            "delete from job_log",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = JobLogDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(JobLogDO row);

    @SelectProvider(type = JobLogDOSqlProvider.class, method = "selectByExampleWithBLOBs")
    List<JobLogDO> selectByExampleWithBLOBs(JobLogDOExample example);

    @SelectProvider(type = JobLogDOSqlProvider.class, method = "selectByExample")
    List<JobLogDO> selectByExample(JobLogDOExample example);

    @Select({
            "select",
            "id, job_group_id, job_id, executor_address, executor_app, executor_handler, ",
            "executor_param, executor_sharding_param, executor_fail_retry_count, trigger_time, ",
            "trigger_code, handle_time, handle_code, alarm_status, created_at, updated_at, ",
            "trigger_msg, handle_msg",
            "from job_log",
            "where id = #{id,jdbcType=BIGINT}"
    })
    JobLogDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = JobLogDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("row") JobLogDO row, @Param("example") JobLogDOExample example);

    @UpdateProvider(type = JobLogDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(JobLogDO row);
}