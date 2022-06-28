package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.JobInfoDO;
import com.wx.dao.warehouse.model.JobInfoDOExample.Criteria;
import com.wx.dao.warehouse.model.JobInfoDOExample.Criterion;
import com.wx.dao.warehouse.model.JobInfoDOExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class JobInfoDOSqlProvider {
    public String countByExample(JobInfoDOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("job_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(JobInfoDOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("job_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(JobInfoDO row) {
        SQL sql = new SQL();
        sql.INSERT_INTO("job_info");

        if (row.getJobGroupId() != null) {
            sql.VALUES("job_group_id", "#{jobGroupId,jdbcType=BIGINT}");
        }

        if (row.getJobDesc() != null) {
            sql.VALUES("job_desc", "#{jobDesc,jdbcType=VARCHAR}");
        }

        if (row.getAlarmEmail() != null) {
            sql.VALUES("alarm_email", "#{alarmEmail,jdbcType=VARCHAR}");
        }

        if (row.getScheduleType() != null) {
            sql.VALUES("schedule_type", "#{scheduleType,jdbcType=VARCHAR}");
        }

        if (row.getScheduleConfig() != null) {
            sql.VALUES("schedule_config", "#{scheduleConfig,jdbcType=VARCHAR}");
        }

        if (row.getExecutorRouteStrategy() != null) {
            sql.VALUES("executor_route_strategy", "#{executorRouteStrategy,jdbcType=VARCHAR}");
        }

        if (row.getExecutorApp() != null) {
            sql.VALUES("executor_app", "#{executorApp,jdbcType=VARCHAR}");
        }

        if (row.getExecutorHandler() != null) {
            sql.VALUES("executor_handler", "#{executorHandler,jdbcType=VARCHAR}");
        }

        if (row.getExecutorParam() != null) {
            sql.VALUES("executor_param", "#{executorParam,jdbcType=VARCHAR}");
        }

        if (row.getExecutorBlockStrategy() != null) {
            sql.VALUES("executor_block_strategy", "#{executorBlockStrategy,jdbcType=VARCHAR}");
        }

        if (row.getExecutorTimeout() != null) {
            sql.VALUES("executor_timeout", "#{executorTimeout,jdbcType=INTEGER}");
        }

        if (row.getExecutorFailRetryCount() != null) {
            sql.VALUES("executor_fail_retry_count", "#{executorFailRetryCount,jdbcType=INTEGER}");
        }

        if (row.getChildJobId() != null) {
            sql.VALUES("child_job_id", "#{childJobId,jdbcType=VARCHAR}");
        }

        if (row.getTriggerStatus() != null) {
            sql.VALUES("trigger_status", "#{triggerStatus,jdbcType=TINYINT}");
        }

        if (row.getTriggerLastTime() != null) {
            sql.VALUES("trigger_last_time", "#{triggerLastTime,jdbcType=BIGINT}");
        }

        if (row.getTriggerNextTime() != null) {
            sql.VALUES("trigger_next_time", "#{triggerNextTime,jdbcType=BIGINT}");
        }

        if (row.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String selectByExample(JobInfoDOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("job_group_id");
        sql.SELECT("job_desc");
        sql.SELECT("alarm_email");
        sql.SELECT("schedule_type");
        sql.SELECT("schedule_config");
        sql.SELECT("executor_route_strategy");
        sql.SELECT("executor_app");
        sql.SELECT("executor_handler");
        sql.SELECT("executor_param");
        sql.SELECT("executor_block_strategy");
        sql.SELECT("executor_timeout");
        sql.SELECT("executor_fail_retry_count");
        sql.SELECT("child_job_id");
        sql.SELECT("trigger_status");
        sql.SELECT("trigger_last_time");
        sql.SELECT("trigger_next_time");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.FROM("job_info");
        applyWhere(sql, example, false);

        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }

        if (example != null && example.getLimit() != null) {
            if (example.getOffset() == null) {
                return sql.toString() + " limit " + example.getLimit();
            } else {
                return sql.toString() + " limit " + example.getOffset() + ", " + example.getLimit();
            }
        }

        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        JobInfoDO row = (JobInfoDO) parameter.get("row");
        JobInfoDOExample example = (JobInfoDOExample) parameter.get("example");

        SQL sql = new SQL();
        sql.UPDATE("job_info");

        if (row.getId() != null) {
            sql.SET("id = #{row.id,jdbcType=BIGINT}");
        }

        if (row.getJobGroupId() != null) {
            sql.SET("job_group_id = #{row.jobGroupId,jdbcType=BIGINT}");
        }

        if (row.getJobDesc() != null) {
            sql.SET("job_desc = #{row.jobDesc,jdbcType=VARCHAR}");
        }

        if (row.getAlarmEmail() != null) {
            sql.SET("alarm_email = #{row.alarmEmail,jdbcType=VARCHAR}");
        }

        if (row.getScheduleType() != null) {
            sql.SET("schedule_type = #{row.scheduleType,jdbcType=VARCHAR}");
        }

        if (row.getScheduleConfig() != null) {
            sql.SET("schedule_config = #{row.scheduleConfig,jdbcType=VARCHAR}");
        }

        if (row.getExecutorRouteStrategy() != null) {
            sql.SET("executor_route_strategy = #{row.executorRouteStrategy,jdbcType=VARCHAR}");
        }

        if (row.getExecutorApp() != null) {
            sql.SET("executor_app = #{row.executorApp,jdbcType=VARCHAR}");
        }

        if (row.getExecutorHandler() != null) {
            sql.SET("executor_handler = #{row.executorHandler,jdbcType=VARCHAR}");
        }

        if (row.getExecutorParam() != null) {
            sql.SET("executor_param = #{row.executorParam,jdbcType=VARCHAR}");
        }

        if (row.getExecutorBlockStrategy() != null) {
            sql.SET("executor_block_strategy = #{row.executorBlockStrategy,jdbcType=VARCHAR}");
        }

        if (row.getExecutorTimeout() != null) {
            sql.SET("executor_timeout = #{row.executorTimeout,jdbcType=INTEGER}");
        }

        if (row.getExecutorFailRetryCount() != null) {
            sql.SET("executor_fail_retry_count = #{row.executorFailRetryCount,jdbcType=INTEGER}");
        }

        if (row.getChildJobId() != null) {
            sql.SET("child_job_id = #{row.childJobId,jdbcType=VARCHAR}");
        }

        if (row.getTriggerStatus() != null) {
            sql.SET("trigger_status = #{row.triggerStatus,jdbcType=TINYINT}");
        }

        if (row.getTriggerLastTime() != null) {
            sql.SET("trigger_last_time = #{row.triggerLastTime,jdbcType=BIGINT}");
        }

        if (row.getTriggerNextTime() != null) {
            sql.SET("trigger_next_time = #{row.triggerNextTime,jdbcType=BIGINT}");
        }

        if (row.getCreatedAt() != null) {
            sql.SET("created_at = #{row.createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.SET("updated_at = #{row.updatedAt,jdbcType=TIMESTAMP}");
        }

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(JobInfoDO row) {
        SQL sql = new SQL();
        sql.UPDATE("job_info");

        if (row.getJobGroupId() != null) {
            sql.SET("job_group_id = #{jobGroupId,jdbcType=BIGINT}");
        }

        if (row.getJobDesc() != null) {
            sql.SET("job_desc = #{jobDesc,jdbcType=VARCHAR}");
        }

        if (row.getAlarmEmail() != null) {
            sql.SET("alarm_email = #{alarmEmail,jdbcType=VARCHAR}");
        }

        if (row.getScheduleType() != null) {
            sql.SET("schedule_type = #{scheduleType,jdbcType=VARCHAR}");
        }

        if (row.getScheduleConfig() != null) {
            sql.SET("schedule_config = #{scheduleConfig,jdbcType=VARCHAR}");
        }

        if (row.getExecutorRouteStrategy() != null) {
            sql.SET("executor_route_strategy = #{executorRouteStrategy,jdbcType=VARCHAR}");
        }

        if (row.getExecutorApp() != null) {
            sql.SET("executor_app = #{executorApp,jdbcType=VARCHAR}");
        }

        if (row.getExecutorHandler() != null) {
            sql.SET("executor_handler = #{executorHandler,jdbcType=VARCHAR}");
        }

        if (row.getExecutorParam() != null) {
            sql.SET("executor_param = #{executorParam,jdbcType=VARCHAR}");
        }

        if (row.getExecutorBlockStrategy() != null) {
            sql.SET("executor_block_strategy = #{executorBlockStrategy,jdbcType=VARCHAR}");
        }

        if (row.getExecutorTimeout() != null) {
            sql.SET("executor_timeout = #{executorTimeout,jdbcType=INTEGER}");
        }

        if (row.getExecutorFailRetryCount() != null) {
            sql.SET("executor_fail_retry_count = #{executorFailRetryCount,jdbcType=INTEGER}");
        }

        if (row.getChildJobId() != null) {
            sql.SET("child_job_id = #{childJobId,jdbcType=VARCHAR}");
        }

        if (row.getTriggerStatus() != null) {
            sql.SET("trigger_status = #{triggerStatus,jdbcType=TINYINT}");
        }

        if (row.getTriggerLastTime() != null) {
            sql.SET("trigger_last_time = #{triggerLastTime,jdbcType=BIGINT}");
        }

        if (row.getTriggerNextTime() != null) {
            sql.SET("trigger_next_time = #{triggerNextTime,jdbcType=BIGINT}");
        }

        if (row.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.SET("updated_at = #{updatedAt,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }

    protected void applyWhere(SQL sql, JobInfoDOExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }

        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }

        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }

                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }

                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }

        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}