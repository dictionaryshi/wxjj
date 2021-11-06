package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.SkuStockDO;
import com.wx.dao.warehouse.model.SkuStockDOExample.Criteria;
import com.wx.dao.warehouse.model.SkuStockDOExample.Criterion;
import com.wx.dao.warehouse.model.SkuStockDOExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class SkuStockDOSqlProvider {
    public String countByExample(SkuStockDOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sku_stock");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SkuStockDOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sku_stock");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SkuStockDO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sku_stock");

        if (record.getStockBaseInfoId() != null) {
            sql.VALUES("stock_base_info_id", "#{stockBaseInfoId,jdbcType=BIGINT}");
        }

        if (record.getSkuId() != null) {
            sql.VALUES("sku_id", "#{skuId,jdbcType=BIGINT}");
        }

        if (record.getStock() != null) {
            sql.VALUES("stock", "#{stock,jdbcType=BIGINT}");
        }

        if (record.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String selectByExample(SkuStockDOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("stock_base_info_id");
        sql.SELECT("sku_id");
        sql.SELECT("stock");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.FROM("sku_stock");
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
        SkuStockDO record = (SkuStockDO) parameter.get("record");
        SkuStockDOExample example = (SkuStockDOExample) parameter.get("example");

        SQL sql = new SQL();
        sql.UPDATE("sku_stock");

        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }

        if (record.getStockBaseInfoId() != null) {
            sql.SET("stock_base_info_id = #{record.stockBaseInfoId,jdbcType=BIGINT}");
        }

        if (record.getSkuId() != null) {
            sql.SET("sku_id = #{record.skuId,jdbcType=BIGINT}");
        }

        if (record.getStock() != null) {
            sql.SET("stock = #{record.stock,jdbcType=BIGINT}");
        }

        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdatedAt() != null) {
            sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        }

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SkuStockDO record) {
        SQL sql = new SQL();
        sql.UPDATE("sku_stock");

        if (record.getStockBaseInfoId() != null) {
            sql.SET("stock_base_info_id = #{stockBaseInfoId,jdbcType=BIGINT}");
        }

        if (record.getSkuId() != null) {
            sql.SET("sku_id = #{skuId,jdbcType=BIGINT}");
        }

        if (record.getStock() != null) {
            sql.SET("stock = #{stock,jdbcType=BIGINT}");
        }

        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdatedAt() != null) {
            sql.SET("updated_at = #{updatedAt,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }

    protected void applyWhere(SQL sql, SkuStockDOExample example, boolean includeExamplePhrase) {
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