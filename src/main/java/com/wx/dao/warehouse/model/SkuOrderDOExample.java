package com.wx.dao.warehouse.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SkuOrderDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SkuOrderDOExample() {
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIsNull() {
            addCriterion("confirm_time is null");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIsNotNull() {
            addCriterion("confirm_time is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeEqualTo(Long value) {
            addCriterion("confirm_time =", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotEqualTo(Long value) {
            addCriterion("confirm_time <>", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeGreaterThan(Long value) {
            addCriterion("confirm_time >", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("confirm_time >=", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeLessThan(Long value) {
            addCriterion("confirm_time <", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeLessThanOrEqualTo(Long value) {
            addCriterion("confirm_time <=", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIn(List<Long> values) {
            addCriterion("confirm_time in", values, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotIn(List<Long> values) {
            addCriterion("confirm_time not in", values, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeBetween(Long value1, Long value2) {
            addCriterion("confirm_time between", value1, value2, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotBetween(Long value1, Long value2) {
            addCriterion("confirm_time not between", value1, value2, "confirmTime");
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

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Long value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Long value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Long value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Long value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Long value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Long> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Long> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Long value1, Long value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Long value1, Long value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneIsNull() {
            addCriterion("customer_phone is null");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneIsNotNull() {
            addCriterion("customer_phone is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneEqualTo(String value) {
            addCriterion("customer_phone =", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneNotEqualTo(String value) {
            addCriterion("customer_phone <>", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneGreaterThan(String value) {
            addCriterion("customer_phone >", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("customer_phone >=", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneLessThan(String value) {
            addCriterion("customer_phone <", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneLessThanOrEqualTo(String value) {
            addCriterion("customer_phone <=", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneLike(String value) {
            addCriterion("customer_phone like", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneNotLike(String value) {
            addCriterion("customer_phone not like", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneIn(List<String> values) {
            addCriterion("customer_phone in", values, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneNotIn(List<String> values) {
            addCriterion("customer_phone not in", values, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneBetween(String value1, String value2) {
            addCriterion("customer_phone between", value1, value2, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneNotBetween(String value1, String value2) {
            addCriterion("customer_phone not between", value1, value2, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressIsNull() {
            addCriterion("customer_address is null");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressIsNotNull() {
            addCriterion("customer_address is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressEqualTo(String value) {
            addCriterion("customer_address =", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotEqualTo(String value) {
            addCriterion("customer_address <>", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressGreaterThan(String value) {
            addCriterion("customer_address >", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressGreaterThanOrEqualTo(String value) {
            addCriterion("customer_address >=", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLessThan(String value) {
            addCriterion("customer_address <", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLessThanOrEqualTo(String value) {
            addCriterion("customer_address <=", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLike(String value) {
            addCriterion("customer_address like", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotLike(String value) {
            addCriterion("customer_address not like", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressIn(List<String> values) {
            addCriterion("customer_address in", values, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotIn(List<String> values) {
            addCriterion("customer_address not in", values, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressBetween(String value1, String value2) {
            addCriterion("customer_address between", value1, value2, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotBetween(String value1, String value2) {
            addCriterion("customer_address not between", value1, value2, "customerAddress");
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