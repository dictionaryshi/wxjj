package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.JobLogDO;
import com.wx.dao.warehouse.model.JobLogDOExample.Criteria;
import com.wx.dao.warehouse.model.JobLogDOExample.Criterion;
import com.wx.dao.warehouse.model.JobLogDOExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class JobLogDOSqlProvider {
    public String countByExample(JobLogDOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("job_log");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(JobLogDOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("job_log");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(JobLogDO row) {
        SQL sql = new SQL();
        sql.INSERT_INTO("job_log");

        if (row.getJobGroupId() != null) {
            sql.VALUES("job_group_id", "#{jobGroupId,jdbcType=BIGINT}");
        }

        if (row.getJobId() != null) {
            sql.VALUES("job_id", "#{jobId,jdbcType=BIGINT}");
        }

        if (row.getExecutorAddress() != null) {
            sql.VALUES("executor_address", "#{executorAddress,jdbcType=VARCHAR}");
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

        if (row.getExecutorShardingParam() != null) {
            sql.VALUES("executor_sharding_param", "#{executorShardingParam,jdbcType=VARCHAR}");
        }

        if (row.getExecutorFailRetryCount() != null) {
            sql.VALUES("executor_fail_retry_count", "#{executorFailRetryCount,jdbcType=INTEGER}");
        }

        if (row.getTriggerTime() != null) {
            sql.VALUES("trigger_time", "#{triggerTime,jdbcType=BIGINT}");
        }

        if (row.getTriggerCode() != null) {
            sql.VALUES("trigger_code", "#{triggerCode,jdbcType=INTEGER}");
        }

        if (row.getHandleTime() != null) {
            sql.VALUES("handle_time", "#{handleTime,jdbcType=BIGINT}");
        }

        if (row.getHandleCode() != null) {
            sql.VALUES("handle_code", "#{handleCode,jdbcType=INTEGER}");
        }

        if (row.getAlarmStatus() != null) {
            sql.VALUES("alarm_status", "#{alarmStatus,jdbcType=TINYINT}");
        }

        if (row.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }

        if (row.getTriggerMsg() != null) {
            sql.VALUES("trigger_msg", "#{triggerMsg,jdbcType=LONGVARCHAR}");
        }

        if (row.getHandleMsg() != null) {
            sql.VALUES("handle_msg", "#{handleMsg,jdbcType=LONGVARCHAR}");
        }

        return sql.toString();
    }

    public String selectByExampleWithBLOBs(JobLogDOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("job_group_id");
        sql.SELECT("job_id");
        sql.SELECT("executor_address");
        sql.SELECT("executor_app");
        sql.SELECT("executor_handler");
        sql.SELECT("executor_param");
        sql.SELECT("executor_sharding_param");
        sql.SELECT("executor_fail_retry_count");
        sql.SELECT("trigger_time");
        sql.SELECT("trigger_code");
        sql.SELECT("handle_time");
        sql.SELECT("handle_code");
        sql.SELECT("alarm_status");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.SELECT("trigger_msg");
        sql.SELECT("handle_msg");
        sql.FROM("job_log");
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

    public String selectByExample(JobLogDOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("job_group_id");
        sql.SELECT("job_id");
        sql.SELECT("executor_address");
        sql.SELECT("executor_app");
        sql.SELECT("executor_handler");
        sql.SELECT("executor_param");
        sql.SELECT("executor_sharding_param");
        sql.SELECT("executor_fail_retry_count");
        sql.SELECT("trigger_time");
        sql.SELECT("trigger_code");
        sql.SELECT("handle_time");
        sql.SELECT("handle_code");
        sql.SELECT("alarm_status");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.FROM("job_log");
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
        JobLogDO row = (JobLogDO) parameter.get("row");
        JobLogDOExample example = (JobLogDOExample) parameter.get("example");

        SQL sql = new SQL();
        sql.UPDATE("job_log");

        if (row.getId() != null) {
            sql.SET("id = #{row.id,jdbcType=BIGINT}");
        }

        if (row.getJobGroupId() != null) {
            sql.SET("job_group_id = #{row.jobGroupId,jdbcType=BIGINT}");
        }

        if (row.getJobId() != null) {
            sql.SET("job_id = #{row.jobId,jdbcType=BIGINT}");
        }

        if (row.getExecutorAddress() != null) {
            sql.SET("executor_address = #{row.executorAddress,jdbcType=VARCHAR}");
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

        if (row.getExecutorShardingParam() != null) {
            sql.SET("executor_sharding_param = #{row.executorShardingParam,jdbcType=VARCHAR}");
        }

        if (row.getExecutorFailRetryCount() != null) {
            sql.SET("executor_fail_retry_count = #{row.executorFailRetryCount,jdbcType=INTEGER}");
        }

        if (row.getTriggerTime() != null) {
            sql.SET("trigger_time = #{row.triggerTime,jdbcType=BIGINT}");
        }

        if (row.getTriggerCode() != null) {
            sql.SET("trigger_code = #{row.triggerCode,jdbcType=INTEGER}");
        }

        if (row.getHandleTime() != null) {
            sql.SET("handle_time = #{row.handleTime,jdbcType=BIGINT}");
        }

        if (row.getHandleCode() != null) {
            sql.SET("handle_code = #{row.handleCode,jdbcType=INTEGER}");
        }

        if (row.getAlarmStatus() != null) {
            sql.SET("alarm_status = #{row.alarmStatus,jdbcType=TINYINT}");
        }

        if (row.getCreatedAt() != null) {
            sql.SET("created_at = #{row.createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.SET("updated_at = #{row.updatedAt,jdbcType=TIMESTAMP}");
        }

        if (row.getTriggerMsg() != null) {
            sql.SET("trigger_msg = #{row.triggerMsg,jdbcType=LONGVARCHAR}");
        }

        if (row.getHandleMsg() != null) {
            sql.SET("handle_msg = #{row.handleMsg,jdbcType=LONGVARCHAR}");
        }

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(JobLogDO row) {
        SQL sql = new SQL();
        sql.UPDATE("job_log");

        if (row.getJobGroupId() != null) {
            sql.SET("job_group_id = #{jobGroupId,jdbcType=BIGINT}");
        }

        if (row.getJobId() != null) {
            sql.SET("job_id = #{jobId,jdbcType=BIGINT}");
        }

        if (row.getExecutorAddress() != null) {
            sql.SET("executor_address = #{executorAddress,jdbcType=VARCHAR}");
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

        if (row.getExecutorShardingParam() != null) {
            sql.SET("executor_sharding_param = #{executorShardingParam,jdbcType=VARCHAR}");
        }

        if (row.getExecutorFailRetryCount() != null) {
            sql.SET("executor_fail_retry_count = #{executorFailRetryCount,jdbcType=INTEGER}");
        }

        if (row.getTriggerTime() != null) {
            sql.SET("trigger_time = #{triggerTime,jdbcType=BIGINT}");
        }

        if (row.getTriggerCode() != null) {
            sql.SET("trigger_code = #{triggerCode,jdbcType=INTEGER}");
        }

        if (row.getHandleTime() != null) {
            sql.SET("handle_time = #{handleTime,jdbcType=BIGINT}");
        }

        if (row.getHandleCode() != null) {
            sql.SET("handle_code = #{handleCode,jdbcType=INTEGER}");
        }

        if (row.getAlarmStatus() != null) {
            sql.SET("alarm_status = #{alarmStatus,jdbcType=TINYINT}");
        }

        if (row.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.SET("updated_at = #{updatedAt,jdbcType=TIMESTAMP}");
        }

        if (row.getTriggerMsg() != null) {
            sql.SET("trigger_msg = #{triggerMsg,jdbcType=LONGVARCHAR}");
        }

        if (row.getHandleMsg() != null) {
            sql.SET("handle_msg = #{handleMsg,jdbcType=LONGVARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }

    protected void applyWhere(SQL sql, JobLogDOExample example, boolean includeExamplePhrase) {
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