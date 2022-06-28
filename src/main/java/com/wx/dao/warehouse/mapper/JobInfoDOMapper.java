package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.JobInfoDO;
import com.wx.dao.warehouse.model.JobInfoDOExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface JobInfoDOMapper {
    @SelectProvider(type = JobInfoDOSqlProvider.class, method = "countByExample")
    long countByExample(JobInfoDOExample example);

    @DeleteProvider(type = JobInfoDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(JobInfoDOExample example);

    @Delete({
            "delete from job_info",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = JobInfoDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(JobInfoDO row);

    @SelectProvider(type = JobInfoDOSqlProvider.class, method = "selectByExample")
    List<JobInfoDO> selectByExample(JobInfoDOExample example);

    @Select({
            "select",
            "id, job_group_id, job_desc, alarm_email, schedule_type, schedule_config, executor_route_strategy, ",
            "executor_app, executor_handler, executor_param, executor_block_strategy, executor_timeout, ",
            "executor_fail_retry_count, child_job_id, trigger_status, trigger_last_time, ",
            "trigger_next_time, created_at, updated_at",
            "from job_info",
            "where id = #{id,jdbcType=BIGINT}"
    })
    JobInfoDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = JobInfoDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("row") JobInfoDO row, @Param("example") JobInfoDOExample example);

    @UpdateProvider(type = JobInfoDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(JobInfoDO row);
}