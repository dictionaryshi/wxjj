package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.MqMessageDO;
import com.wx.dao.warehouse.model.MqMessageDOExample.Criteria;
import com.wx.dao.warehouse.model.MqMessageDOExample.Criterion;
import com.wx.dao.warehouse.model.MqMessageDOExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class MqMessageDOSqlProvider {
    public String countByExample(MqMessageDOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("mq_message");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(MqMessageDOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("mq_message");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(MqMessageDO row) {
        SQL sql = new SQL();
        sql.INSERT_INTO("mq_message");

        if (row.getTopic() != null) {
            sql.VALUES("topic", "#{topic,jdbcType=VARCHAR}");
        }

        if (row.getMqGroup() != null) {
            sql.VALUES("mq_group", "#{mqGroup,jdbcType=VARCHAR}");
        }

        if (row.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=TINYINT}");
        }

        if (row.getRetryCount() != null) {
            sql.VALUES("retry_count", "#{retryCount,jdbcType=INTEGER}");
        }

        if (row.getShardingId() != null) {
            sql.VALUES("sharding_id", "#{shardingId,jdbcType=BIGINT}");
        }

        if (row.getTimeout() != null) {
            sql.VALUES("timeout", "#{timeout,jdbcType=INTEGER}");
        }

        if (row.getEffectTime() != null) {
            sql.VALUES("effect_time", "#{effectTime,jdbcType=BIGINT}");
        }

        if (row.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }

        if (row.getData() != null) {
            sql.VALUES("data", "#{data,jdbcType=LONGVARCHAR}");
        }

        if (row.getLog() != null) {
            sql.VALUES("log", "#{log,jdbcType=LONGVARCHAR}");
        }

        return sql.toString();
    }

    public String selectByExampleWithBLOBs(MqMessageDOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("topic");
        sql.SELECT("mq_group");
        sql.SELECT("status");
        sql.SELECT("retry_count");
        sql.SELECT("sharding_id");
        sql.SELECT("timeout");
        sql.SELECT("effect_time");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.SELECT("data");
        sql.SELECT("log");
        sql.FROM("mq_message");
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

    public String selectByExample(MqMessageDOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("topic");
        sql.SELECT("mq_group");
        sql.SELECT("status");
        sql.SELECT("retry_count");
        sql.SELECT("sharding_id");
        sql.SELECT("timeout");
        sql.SELECT("effect_time");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.FROM("mq_message");
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
        MqMessageDO row = (MqMessageDO) parameter.get("row");
        MqMessageDOExample example = (MqMessageDOExample) parameter.get("example");

        SQL sql = new SQL();
        sql.UPDATE("mq_message");

        if (row.getId() != null) {
            sql.SET("id = #{row.id,jdbcType=BIGINT}");
        }

        if (row.getTopic() != null) {
            sql.SET("topic = #{row.topic,jdbcType=VARCHAR}");
        }

        if (row.getMqGroup() != null) {
            sql.SET("mq_group = #{row.mqGroup,jdbcType=VARCHAR}");
        }

        if (row.getStatus() != null) {
            sql.SET("status = #{row.status,jdbcType=TINYINT}");
        }

        if (row.getRetryCount() != null) {
            sql.SET("retry_count = #{row.retryCount,jdbcType=INTEGER}");
        }

        if (row.getShardingId() != null) {
            sql.SET("sharding_id = #{row.shardingId,jdbcType=BIGINT}");
        }

        if (row.getTimeout() != null) {
            sql.SET("timeout = #{row.timeout,jdbcType=INTEGER}");
        }

        if (row.getEffectTime() != null) {
            sql.SET("effect_time = #{row.effectTime,jdbcType=BIGINT}");
        }

        if (row.getCreatedAt() != null) {
            sql.SET("created_at = #{row.createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.SET("updated_at = #{row.updatedAt,jdbcType=TIMESTAMP}");
        }

        if (row.getData() != null) {
            sql.SET("data = #{row.data,jdbcType=LONGVARCHAR}");
        }

        if (row.getLog() != null) {
            sql.SET("log = #{row.log,jdbcType=LONGVARCHAR}");
        }

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(MqMessageDO row) {
        SQL sql = new SQL();
        sql.UPDATE("mq_message");

        if (row.getTopic() != null) {
            sql.SET("topic = #{topic,jdbcType=VARCHAR}");
        }

        if (row.getMqGroup() != null) {
            sql.SET("mq_group = #{mqGroup,jdbcType=VARCHAR}");
        }

        if (row.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=TINYINT}");
        }

        if (row.getRetryCount() != null) {
            sql.SET("retry_count = #{retryCount,jdbcType=INTEGER}");
        }

        if (row.getShardingId() != null) {
            sql.SET("sharding_id = #{shardingId,jdbcType=BIGINT}");
        }

        if (row.getTimeout() != null) {
            sql.SET("timeout = #{timeout,jdbcType=INTEGER}");
        }

        if (row.getEffectTime() != null) {
            sql.SET("effect_time = #{effectTime,jdbcType=BIGINT}");
        }

        if (row.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.SET("updated_at = #{updatedAt,jdbcType=TIMESTAMP}");
        }

        if (row.getData() != null) {
            sql.SET("data = #{data,jdbcType=LONGVARCHAR}");
        }

        if (row.getLog() != null) {
            sql.SET("log = #{log,jdbcType=LONGVARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }

    protected void applyWhere(SQL sql, MqMessageDOExample example, boolean includeExamplePhrase) {
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