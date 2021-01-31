package com.wx.dao.warehouse.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SkuStockDetailDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SkuStockDetailDOExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStockBaseInfoIdIsNull() {
            addCriterion("stock_base_info_id is null");
            return (Criteria) this;
        }

        public Criteria andStockBaseInfoIdIsNotNull() {
            addCriterion("stock_base_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andStockBaseInfoIdEqualTo(Long value) {
            addCriterion("stock_base_info_id =", value, "stockBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andStockBaseInfoIdNotEqualTo(Long value) {
            addCriterion("stock_base_info_id <>", value, "stockBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andStockBaseInfoIdGreaterThan(Long value) {
            addCriterion("stock_base_info_id >", value, "stockBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andStockBaseInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("stock_base_info_id >=", value, "stockBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andStockBaseInfoIdLessThan(Long value) {
            addCriterion("stock_base_info_id <", value, "stockBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andStockBaseInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("stock_base_info_id <=", value, "stockBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andStockBaseInfoIdIn(List<Long> values) {
            addCriterion("stock_base_info_id in", values, "stockBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andStockBaseInfoIdNotIn(List<Long> values) {
            addCriterion("stock_base_info_id not in", values, "stockBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andStockBaseInfoIdBetween(Long value1, Long value2) {
            addCriterion("stock_base_info_id between", value1, value2, "stockBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andStockBaseInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("stock_base_info_id not between", value1, value2, "stockBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNull() {
            addCriterion("sku_id is null");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNotNull() {
            addCriterion("sku_id is not null");
            return (Criteria) this;
        }

        public Criteria andSkuIdEqualTo(Long value) {
            addCriterion("sku_id =", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotEqualTo(Long value) {
            addCriterion("sku_id <>", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThan(Long value) {
            addCriterion("sku_id >", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sku_id >=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThan(Long value) {
            addCriterion("sku_id <", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThanOrEqualTo(Long value) {
            addCriterion("sku_id <=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIn(List<Long> values) {
            addCriterion("sku_id in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotIn(List<Long> values) {
            addCriterion("sku_id not in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdBetween(Long value1, Long value2) {
            addCriterion("sku_id between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotBetween(Long value1, Long value2) {
            addCriterion("sku_id not between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStockOffsetIsNull() {
            addCriterion("stock_offset is null");
            return (Criteria) this;
        }

        public Criteria andStockOffsetIsNotNull() {
            addCriterion("stock_offset is not null");
            return (Criteria) this;
        }

        public Criteria andStockOffsetEqualTo(Long value) {
            addCriterion("stock_offset =", value, "stockOffset");
            return (Criteria) this;
        }

        public Criteria andStockOffsetNotEqualTo(Long value) {
            addCriterion("stock_offset <>", value, "stockOffset");
            return (Criteria) this;
        }

        public Criteria andStockOffsetGreaterThan(Long value) {
            addCriterion("stock_offset >", value, "stockOffset");
            return (Criteria) this;
        }

        public Criteria andStockOffsetGreaterThanOrEqualTo(Long value) {
            addCriterion("stock_offset >=", value, "stockOffset");
            return (Criteria) this;
        }

        public Criteria andStockOffsetLessThan(Long value) {
            addCriterion("stock_offset <", value, "stockOffset");
            return (Criteria) this;
        }

        public Criteria andStockOffsetLessThanOrEqualTo(Long value) {
            addCriterion("stock_offset <=", value, "stockOffset");
            return (Criteria) this;
        }

        public Criteria andStockOffsetIn(List<Long> values) {
            addCriterion("stock_offset in", values, "stockOffset");
            return (Criteria) this;
        }

        public Criteria andStockOffsetNotIn(List<Long> values) {
            addCriterion("stock_offset not in", values, "stockOffset");
            return (Criteria) this;
        }

        public Criteria andStockOffsetBetween(Long value1, Long value2) {
            addCriterion("stock_offset between", value1, value2, "stockOffset");
            return (Criteria) this;
        }

        public Criteria andStockOffsetNotBetween(Long value1, Long value2) {
            addCriterion("stock_offset not between", value1, value2, "stockOffset");
            return (Criteria) this;
        }

        public Criteria andStockBeforeIsNull() {
            addCriterion("stock_before is null");
            return (Criteria) this;
        }

        public Criteria andStockBeforeIsNotNull() {
            addCriterion("stock_before is not null");
            return (Criteria) this;
        }

        public Criteria andStockBeforeEqualTo(Long value) {
            addCriterion("stock_before =", value, "stockBefore");
            return (Criteria) this;
        }

        public Criteria andStockBeforeNotEqualTo(Long value) {
            addCriterion("stock_before <>", value, "stockBefore");
            return (Criteria) this;
        }

        public Criteria andStockBeforeGreaterThan(Long value) {
            addCriterion("stock_before >", value, "stockBefore");
            return (Criteria) this;
        }

        public Criteria andStockBeforeGreaterThanOrEqualTo(Long value) {
            addCriterion("stock_before >=", value, "stockBefore");
            return (Criteria) this;
        }

        public Criteria andStockBeforeLessThan(Long value) {
            addCriterion("stock_before <", value, "stockBefore");
            return (Criteria) this;
        }

        public Criteria andStockBeforeLessThanOrEqualTo(Long value) {
            addCriterion("stock_before <=", value, "stockBefore");
            return (Criteria) this;
        }

        public Criteria andStockBeforeIn(List<Long> values) {
            addCriterion("stock_before in", values, "stockBefore");
            return (Criteria) this;
        }

        public Criteria andStockBeforeNotIn(List<Long> values) {
            addCriterion("stock_before not in", values, "stockBefore");
            return (Criteria) this;
        }

        public Criteria andStockBeforeBetween(Long value1, Long value2) {
            addCriterion("stock_before between", value1, value2, "stockBefore");
            return (Criteria) this;
        }

        public Criteria andStockBeforeNotBetween(Long value1, Long value2) {
            addCriterion("stock_before not between", value1, value2, "stockBefore");
            return (Criteria) this;
        }

        public Criteria andStockAfterIsNull() {
            addCriterion("stock_after is null");
            return (Criteria) this;
        }

        public Criteria andStockAfterIsNotNull() {
            addCriterion("stock_after is not null");
            return (Criteria) this;
        }

        public Criteria andStockAfterEqualTo(Long value) {
            addCriterion("stock_after =", value, "stockAfter");
            return (Criteria) this;
        }

        public Criteria andStockAfterNotEqualTo(Long value) {
            addCriterion("stock_after <>", value, "stockAfter");
            return (Criteria) this;
        }

        public Criteria andStockAfterGreaterThan(Long value) {
            addCriterion("stock_after >", value, "stockAfter");
            return (Criteria) this;
        }

        public Criteria andStockAfterGreaterThanOrEqualTo(Long value) {
            addCriterion("stock_after >=", value, "stockAfter");
            return (Criteria) this;
        }

        public Criteria andStockAfterLessThan(Long value) {
            addCriterion("stock_after <", value, "stockAfter");
            return (Criteria) this;
        }

        public Criteria andStockAfterLessThanOrEqualTo(Long value) {
            addCriterion("stock_after <=", value, "stockAfter");
            return (Criteria) this;
        }

        public Criteria andStockAfterIn(List<Long> values) {
            addCriterion("stock_after in", values, "stockAfter");
            return (Criteria) this;
        }

        public Criteria andStockAfterNotIn(List<Long> values) {
            addCriterion("stock_after not in", values, "stockAfter");
            return (Criteria) this;
        }

        public Criteria andStockAfterBetween(Long value1, Long value2) {
            addCriterion("stock_after between", value1, value2, "stockAfter");
            return (Criteria) this;
        }

        public Criteria andStockAfterNotBetween(Long value1, Long value2) {
            addCriterion("stock_after not between", value1, value2, "stockAfter");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(Long value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(Long value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(Long value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(Long value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(Long value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(Long value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<Long> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<Long> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(Long value1, Long value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(Long value1, Long value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}