package com.wx.dao.warehouse.mapper;

import com.wx.dao.warehouse.model.SkuOrderDO;
import com.wx.dao.warehouse.model.SkuOrderDOExample.Criteria;
import com.wx.dao.warehouse.model.SkuOrderDOExample.Criterion;
import com.wx.dao.warehouse.model.SkuOrderDOExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class SkuOrderDOSqlProvider {
    public String countByExample(SkuOrderDOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sku_order");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SkuOrderDOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sku_order");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SkuOrderDO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sku_order");

        if (record.getOrderId() != null) {
            sql.VALUES("order_id", "#{orderId,jdbcType=BIGINT}");
        }

        if (record.getStockBaseInfoId() != null) {
            sql.VALUES("stock_base_info_id", "#{stockBaseInfoId,jdbcType=BIGINT}");
        }

        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=TINYINT}");
        }

        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=TINYINT}");
        }

        if (record.getConfirmTime() != null) {
            sql.VALUES("confirm_time", "#{confirmTime,jdbcType=BIGINT}");
        }

        if (record.getOperator() != null) {
            sql.VALUES("operator", "#{operator,jdbcType=BIGINT}");
        }

        if (record.getPrice() != null) {
            sql.VALUES("price", "#{price,jdbcType=BIGINT}");
        }

        if (record.getCustomerName() != null) {
            sql.VALUES("customer_name", "#{customerName,jdbcType=VARCHAR}");
        }

        if (record.getCustomerPhone() != null) {
            sql.VALUES("customer_phone", "#{customerPhone,jdbcType=VARCHAR}");
        }

        if (record.getCustomerAddress() != null) {
            sql.VALUES("customer_address", "#{customerAddress,jdbcType=VARCHAR}");
        }

        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }

        if (record.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String selectByExample(SkuOrderDOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("order_id");
        sql.SELECT("stock_base_info_id");
        sql.SELECT("type");
        sql.SELECT("status");
        sql.SELECT("confirm_time");
        sql.SELECT("operator");
        sql.SELECT("price");
        sql.SELECT("customer_name");
        sql.SELECT("customer_phone");
        sql.SELECT("customer_address");
        sql.SELECT("remark");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.FROM("sku_order");
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
        SkuOrderDO record = (SkuOrderDO) parameter.get("record");
        SkuOrderDOExample example = (SkuOrderDOExample) parameter.get("example");

        SQL sql = new SQL();
        sql.UPDATE("sku_order");

        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }

        if (record.getOrderId() != null) {
            sql.SET("order_id = #{record.orderId,jdbcType=BIGINT}");
        }

        if (record.getStockBaseInfoId() != null) {
            sql.SET("stock_base_info_id = #{record.stockBaseInfoId,jdbcType=BIGINT}");
        }

        if (record.getType() != null) {
            sql.SET("type = #{record.type,jdbcType=TINYINT}");
        }

        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=TINYINT}");
        }

        if (record.getConfirmTime() != null) {
            sql.SET("confirm_time = #{record.confirmTime,jdbcType=BIGINT}");
        }

        if (record.getOperator() != null) {
            sql.SET("operator = #{record.operator,jdbcType=BIGINT}");
        }

        if (record.getPrice() != null) {
            sql.SET("price = #{record.price,jdbcType=BIGINT}");
        }

        if (record.getCustomerName() != null) {
            sql.SET("customer_name = #{record.customerName,jdbcType=VARCHAR}");
        }

        if (record.getCustomerPhone() != null) {
            sql.SET("customer_phone = #{record.customerPhone,jdbcType=VARCHAR}");
        }

        if (record.getCustomerAddress() != null) {
            sql.SET("customer_address = #{record.customerAddress,jdbcType=VARCHAR}");
        }

        if (record.getRemark() != null) {
            sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
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

    public String updateByPrimaryKeySelective(SkuOrderDO record) {
        SQL sql = new SQL();
        sql.UPDATE("sku_order");

        if (record.getOrderId() != null) {
            sql.SET("order_id = #{orderId,jdbcType=BIGINT}");
        }

        if (record.getStockBaseInfoId() != null) {
            sql.SET("stock_base_info_id = #{stockBaseInfoId,jdbcType=BIGINT}");
        }

        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=TINYINT}");
        }

        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=TINYINT}");
        }

        if (record.getConfirmTime() != null) {
            sql.SET("confirm_time = #{confirmTime,jdbcType=BIGINT}");
        }

        if (record.getOperator() != null) {
            sql.SET("operator = #{operator,jdbcType=BIGINT}");
        }

        if (record.getPrice() != null) {
            sql.SET("price = #{price,jdbcType=BIGINT}");
        }

        if (record.getCustomerName() != null) {
            sql.SET("customer_name = #{customerName,jdbcType=VARCHAR}");
        }

        if (record.getCustomerPhone() != null) {
            sql.SET("customer_phone = #{customerPhone,jdbcType=VARCHAR}");
        }

        if (record.getCustomerAddress() != null) {
            sql.SET("customer_address = #{customerAddress,jdbcType=VARCHAR}");
        }

        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
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

    protected void applyWhere(SQL sql, SkuOrderDOExample example, boolean includeExamplePhrase) {
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