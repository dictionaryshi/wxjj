package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.JobGroupDO;
import com.wx.dao.warehouse.model.JobGroupDOExample.Criteria;
import com.wx.dao.warehouse.model.JobGroupDOExample.Criterion;
import com.wx.dao.warehouse.model.JobGroupDOExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class JobGroupDOSqlProvider {
    public String countByExample(JobGroupDOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("job_group");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(JobGroupDOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("job_group");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(JobGroupDO row) {
        SQL sql = new SQL();
        sql.INSERT_INTO("job_group");

        if (row.getAppName() != null) {
            sql.VALUES("app_name", "#{appName,jdbcType=VARCHAR}");
        }

        if (row.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (row.getAddressType() != null) {
            sql.VALUES("address_type", "#{addressType,jdbcType=TINYINT}");
        }

        if (row.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }

        if (row.getAddressList() != null) {
            sql.VALUES("address_list", "#{addressList,jdbcType=LONGVARCHAR}");
        }

        return sql.toString();
    }

    public String selectByExampleWithBLOBs(JobGroupDOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("app_name");
        sql.SELECT("name");
        sql.SELECT("address_type");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.SELECT("address_list");
        sql.FROM("job_group");
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

    public String selectByExample(JobGroupDOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("app_name");
        sql.SELECT("name");
        sql.SELECT("address_type");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.FROM("job_group");
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
        JobGroupDO row = (JobGroupDO) parameter.get("row");
        JobGroupDOExample example = (JobGroupDOExample) parameter.get("example");

        SQL sql = new SQL();
        sql.UPDATE("job_group");

        if (row.getId() != null) {
            sql.SET("id = #{row.id,jdbcType=BIGINT}");
        }

        if (row.getAppName() != null) {
            sql.SET("app_name = #{row.appName,jdbcType=VARCHAR}");
        }

        if (row.getName() != null) {
            sql.SET("name = #{row.name,jdbcType=VARCHAR}");
        }

        if (row.getAddressType() != null) {
            sql.SET("address_type = #{row.addressType,jdbcType=TINYINT}");
        }

        if (row.getCreatedAt() != null) {
            sql.SET("created_at = #{row.createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.SET("updated_at = #{row.updatedAt,jdbcType=TIMESTAMP}");
        }

        if (row.getAddressList() != null) {
            sql.SET("address_list = #{row.addressList,jdbcType=LONGVARCHAR}");
        }

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(JobGroupDO row) {
        SQL sql = new SQL();
        sql.UPDATE("job_group");

        if (row.getAppName() != null) {
            sql.SET("app_name = #{appName,jdbcType=VARCHAR}");
        }

        if (row.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (row.getAddressType() != null) {
            sql.SET("address_type = #{addressType,jdbcType=TINYINT}");
        }

        if (row.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.SET("updated_at = #{updatedAt,jdbcType=TIMESTAMP}");
        }

        if (row.getAddressList() != null) {
            sql.SET("address_list = #{addressList,jdbcType=LONGVARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }

    protected void applyWhere(SQL sql, JobGroupDOExample example, boolean includeExamplePhrase) {
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