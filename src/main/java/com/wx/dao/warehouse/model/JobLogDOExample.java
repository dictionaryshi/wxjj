package com.wx.dao.warehouse.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobLogDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer offset;

    private Integer limit;

    public JobLogDOExample() {
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

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
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

        public Criteria andJobGroupIdIsNull() {
            addCriterion("job_group_id is null");
            return (Criteria) this;
        }

        public Criteria andJobGroupIdIsNotNull() {
            addCriterion("job_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andJobGroupIdEqualTo(Long value) {
            addCriterion("job_group_id =", value, "jobGroupId");
            return (Criteria) this;
        }

        public Criteria andJobGroupIdNotEqualTo(Long value) {
            addCriterion("job_group_id <>", value, "jobGroupId");
            return (Criteria) this;
        }

        public Criteria andJobGroupIdGreaterThan(Long value) {
            addCriterion("job_group_id >", value, "jobGroupId");
            return (Criteria) this;
        }

        public Criteria andJobGroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("job_group_id >=", value, "jobGroupId");
            return (Criteria) this;
        }

        public Criteria andJobGroupIdLessThan(Long value) {
            addCriterion("job_group_id <", value, "jobGroupId");
            return (Criteria) this;
        }

        public Criteria andJobGroupIdLessThanOrEqualTo(Long value) {
            addCriterion("job_group_id <=", value, "jobGroupId");
            return (Criteria) this;
        }

        public Criteria andJobGroupIdIn(List<Long> values) {
            addCriterion("job_group_id in", values, "jobGroupId");
            return (Criteria) this;
        }

        public Criteria andJobGroupIdNotIn(List<Long> values) {
            addCriterion("job_group_id not in", values, "jobGroupId");
            return (Criteria) this;
        }

        public Criteria andJobGroupIdBetween(Long value1, Long value2) {
            addCriterion("job_group_id between", value1, value2, "jobGroupId");
            return (Criteria) this;
        }

        public Criteria andJobGroupIdNotBetween(Long value1, Long value2) {
            addCriterion("job_group_id not between", value1, value2, "jobGroupId");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNull() {
            addCriterion("job_id is null");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNotNull() {
            addCriterion("job_id is not null");
            return (Criteria) this;
        }

        public Criteria andJobIdEqualTo(Long value) {
            addCriterion("job_id =", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotEqualTo(Long value) {
            addCriterion("job_id <>", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThan(Long value) {
            addCriterion("job_id >", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanOrEqualTo(Long value) {
            addCriterion("job_id >=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThan(Long value) {
            addCriterion("job_id <", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanOrEqualTo(Long value) {
            addCriterion("job_id <=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdIn(List<Long> values) {
            addCriterion("job_id in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotIn(List<Long> values) {
            addCriterion("job_id not in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdBetween(Long value1, Long value2) {
            addCriterion("job_id between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotBetween(Long value1, Long value2) {
            addCriterion("job_id not between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressIsNull() {
            addCriterion("executor_address is null");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressIsNotNull() {
            addCriterion("executor_address is not null");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressEqualTo(String value) {
            addCriterion("executor_address =", value, "executorAddress");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressNotEqualTo(String value) {
            addCriterion("executor_address <>", value, "executorAddress");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressGreaterThan(String value) {
            addCriterion("executor_address >", value, "executorAddress");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressGreaterThanOrEqualTo(String value) {
            addCriterion("executor_address >=", value, "executorAddress");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressLessThan(String value) {
            addCriterion("executor_address <", value, "executorAddress");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressLessThanOrEqualTo(String value) {
            addCriterion("executor_address <=", value, "executorAddress");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressLike(String value) {
            addCriterion("executor_address like", value, "executorAddress");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressNotLike(String value) {
            addCriterion("executor_address not like", value, "executorAddress");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressIn(List<String> values) {
            addCriterion("executor_address in", values, "executorAddress");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressNotIn(List<String> values) {
            addCriterion("executor_address not in", values, "executorAddress");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressBetween(String value1, String value2) {
            addCriterion("executor_address between", value1, value2, "executorAddress");
            return (Criteria) this;
        }

        public Criteria andExecutorAddressNotBetween(String value1, String value2) {
            addCriterion("executor_address not between", value1, value2, "executorAddress");
            return (Criteria) this;
        }

        public Criteria andExecutorAppIsNull() {
            addCriterion("executor_app is null");
            return (Criteria) this;
        }

        public Criteria andExecutorAppIsNotNull() {
            addCriterion("executor_app is not null");
            return (Criteria) this;
        }

        public Criteria andExecutorAppEqualTo(String value) {
            addCriterion("executor_app =", value, "executorApp");
            return (Criteria) this;
        }

        public Criteria andExecutorAppNotEqualTo(String value) {
            addCriterion("executor_app <>", value, "executorApp");
            return (Criteria) this;
        }

        public Criteria andExecutorAppGreaterThan(String value) {
            addCriterion("executor_app >", value, "executorApp");
            return (Criteria) this;
        }

        public Criteria andExecutorAppGreaterThanOrEqualTo(String value) {
            addCriterion("executor_app >=", value, "executorApp");
            return (Criteria) this;
        }

        public Criteria andExecutorAppLessThan(String value) {
            addCriterion("executor_app <", value, "executorApp");
            return (Criteria) this;
        }

        public Criteria andExecutorAppLessThanOrEqualTo(String value) {
            addCriterion("executor_app <=", value, "executorApp");
            return (Criteria) this;
        }

        public Criteria andExecutorAppLike(String value) {
            addCriterion("executor_app like", value, "executorApp");
            return (Criteria) this;
        }

        public Criteria andExecutorAppNotLike(String value) {
            addCriterion("executor_app not like", value, "executorApp");
            return (Criteria) this;
        }

        public Criteria andExecutorAppIn(List<String> values) {
            addCriterion("executor_app in", values, "executorApp");
            return (Criteria) this;
        }

        public Criteria andExecutorAppNotIn(List<String> values) {
            addCriterion("executor_app not in", values, "executorApp");
            return (Criteria) this;
        }

        public Criteria andExecutorAppBetween(String value1, String value2) {
            addCriterion("executor_app between", value1, value2, "executorApp");
            return (Criteria) this;
        }

        public Criteria andExecutorAppNotBetween(String value1, String value2) {
            addCriterion("executor_app not between", value1, value2, "executorApp");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerIsNull() {
            addCriterion("executor_handler is null");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerIsNotNull() {
            addCriterion("executor_handler is not null");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerEqualTo(String value) {
            addCriterion("executor_handler =", value, "executorHandler");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerNotEqualTo(String value) {
            addCriterion("executor_handler <>", value, "executorHandler");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerGreaterThan(String value) {
            addCriterion("executor_handler >", value, "executorHandler");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerGreaterThanOrEqualTo(String value) {
            addCriterion("executor_handler >=", value, "executorHandler");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerLessThan(String value) {
            addCriterion("executor_handler <", value, "executorHandler");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerLessThanOrEqualTo(String value) {
            addCriterion("executor_handler <=", value, "executorHandler");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerLike(String value) {
            addCriterion("executor_handler like", value, "executorHandler");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerNotLike(String value) {
            addCriterion("executor_handler not like", value, "executorHandler");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerIn(List<String> values) {
            addCriterion("executor_handler in", values, "executorHandler");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerNotIn(List<String> values) {
            addCriterion("executor_handler not in", values, "executorHandler");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerBetween(String value1, String value2) {
            addCriterion("executor_handler between", value1, value2, "executorHandler");
            return (Criteria) this;
        }

        public Criteria andExecutorHandlerNotBetween(String value1, String value2) {
            addCriterion("executor_handler not between", value1, value2, "executorHandler");
            return (Criteria) this;
        }

        public Criteria andExecutorParamIsNull() {
            addCriterion("executor_param is null");
            return (Criteria) this;
        }

        public Criteria andExecutorParamIsNotNull() {
            addCriterion("executor_param is not null");
            return (Criteria) this;
        }

        public Criteria andExecutorParamEqualTo(String value) {
            addCriterion("executor_param =", value, "executorParam");
            return (Criteria) this;
        }

        public Criteria andExecutorParamNotEqualTo(String value) {
            addCriterion("executor_param <>", value, "executorParam");
            return (Criteria) this;
        }

        public Criteria andExecutorParamGreaterThan(String value) {
            addCriterion("executor_param >", value, "executorParam");
            return (Criteria) this;
        }

        public Criteria andExecutorParamGreaterThanOrEqualTo(String value) {
            addCriterion("executor_param >=", value, "executorParam");
            return (Criteria) this;
        }

        public Criteria andExecutorParamLessThan(String value) {
            addCriterion("executor_param <", value, "executorParam");
            return (Criteria) this;
        }

        public Criteria andExecutorParamLessThanOrEqualTo(String value) {
            addCriterion("executor_param <=", value, "executorParam");
            return (Criteria) this;
        }

        public Criteria andExecutorParamLike(String value) {
            addCriterion("executor_param like", value, "executorParam");
            return (Criteria) this;
        }

        public Criteria andExecutorParamNotLike(String value) {
            addCriterion("executor_param not like", value, "executorParam");
            return (Criteria) this;
        }

        public Criteria andExecutorParamIn(List<String> values) {
            addCriterion("executor_param in", values, "executorParam");
            return (Criteria) this;
        }

        public Criteria andExecutorParamNotIn(List<String> values) {
            addCriterion("executor_param not in", values, "executorParam");
            return (Criteria) this;
        }

        public Criteria andExecutorParamBetween(String value1, String value2) {
            addCriterion("executor_param between", value1, value2, "executorParam");
            return (Criteria) this;
        }

        public Criteria andExecutorParamNotBetween(String value1, String value2) {
            addCriterion("executor_param not between", value1, value2, "executorParam");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamIsNull() {
            addCriterion("executor_sharding_param is null");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamIsNotNull() {
            addCriterion("executor_sharding_param is not null");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamEqualTo(String value) {
            addCriterion("executor_sharding_param =", value, "executorShardingParam");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamNotEqualTo(String value) {
            addCriterion("executor_sharding_param <>", value, "executorShardingParam");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamGreaterThan(String value) {
            addCriterion("executor_sharding_param >", value, "executorShardingParam");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamGreaterThanOrEqualTo(String value) {
            addCriterion("executor_sharding_param >=", value, "executorShardingParam");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamLessThan(String value) {
            addCriterion("executor_sharding_param <", value, "executorShardingParam");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamLessThanOrEqualTo(String value) {
            addCriterion("executor_sharding_param <=", value, "executorShardingParam");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamLike(String value) {
            addCriterion("executor_sharding_param like", value, "executorShardingParam");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamNotLike(String value) {
            addCriterion("executor_sharding_param not like", value, "executorShardingParam");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamIn(List<String> values) {
            addCriterion("executor_sharding_param in", values, "executorShardingParam");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamNotIn(List<String> values) {
            addCriterion("executor_sharding_param not in", values, "executorShardingParam");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamBetween(String value1, String value2) {
            addCriterion("executor_sharding_param between", value1, value2, "executorShardingParam");
            return (Criteria) this;
        }

        public Criteria andExecutorShardingParamNotBetween(String value1, String value2) {
            addCriterion("executor_sharding_param not between", value1, value2, "executorShardingParam");
            return (Criteria) this;
        }

        public Criteria andExecutorFailRetryCountIsNull() {
            addCriterion("executor_fail_retry_count is null");
            return (Criteria) this;
        }

        public Criteria andExecutorFailRetryCountIsNotNull() {
            addCriterion("executor_fail_retry_count is not null");
            return (Criteria) this;
        }

        public Criteria andExecutorFailRetryCountEqualTo(Integer value) {
            addCriterion("executor_fail_retry_count =", value, "executorFailRetryCount");
            return (Criteria) this;
        }

        public Criteria andExecutorFailRetryCountNotEqualTo(Integer value) {
            addCriterion("executor_fail_retry_count <>", value, "executorFailRetryCount");
            return (Criteria) this;
        }

        public Criteria andExecutorFailRetryCountGreaterThan(Integer value) {
            addCriterion("executor_fail_retry_count >", value, "executorFailRetryCount");
            return (Criteria) this;
        }

        public Criteria andExecutorFailRetryCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("executor_fail_retry_count >=", value, "executorFailRetryCount");
            return (Criteria) this;
        }

        public Criteria andExecutorFailRetryCountLessThan(Integer value) {
            addCriterion("executor_fail_retry_count <", value, "executorFailRetryCount");
            return (Criteria) this;
        }

        public Criteria andExecutorFailRetryCountLessThanOrEqualTo(Integer value) {
            addCriterion("executor_fail_retry_count <=", value, "executorFailRetryCount");
            return (Criteria) this;
        }

        public Criteria andExecutorFailRetryCountIn(List<Integer> values) {
            addCriterion("executor_fail_retry_count in", values, "executorFailRetryCount");
            return (Criteria) this;
        }

        public Criteria andExecutorFailRetryCountNotIn(List<Integer> values) {
            addCriterion("executor_fail_retry_count not in", values, "executorFailRetryCount");
            return (Criteria) this;
        }

        public Criteria andExecutorFailRetryCountBetween(Integer value1, Integer value2) {
            addCriterion("executor_fail_retry_count between", value1, value2, "executorFailRetryCount");
            return (Criteria) this;
        }

        public Criteria andExecutorFailRetryCountNotBetween(Integer value1, Integer value2) {
            addCriterion("executor_fail_retry_count not between", value1, value2, "executorFailRetryCount");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeIsNull() {
            addCriterion("trigger_time is null");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeIsNotNull() {
            addCriterion("trigger_time is not null");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeEqualTo(Long value) {
            addCriterion("trigger_time =", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeNotEqualTo(Long value) {
            addCriterion("trigger_time <>", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeGreaterThan(Long value) {
            addCriterion("trigger_time >", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("trigger_time >=", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeLessThan(Long value) {
            addCriterion("trigger_time <", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeLessThanOrEqualTo(Long value) {
            addCriterion("trigger_time <=", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeIn(List<Long> values) {
            addCriterion("trigger_time in", values, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeNotIn(List<Long> values) {
            addCriterion("trigger_time not in", values, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeBetween(Long value1, Long value2) {
            addCriterion("trigger_time between", value1, value2, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeNotBetween(Long value1, Long value2) {
            addCriterion("trigger_time not between", value1, value2, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerCodeIsNull() {
            addCriterion("trigger_code is null");
            return (Criteria) this;
        }

        public Criteria andTriggerCodeIsNotNull() {
            addCriterion("trigger_code is not null");
            return (Criteria) this;
        }

        public Criteria andTriggerCodeEqualTo(Integer value) {
            addCriterion("trigger_code =", value, "triggerCode");
            return (Criteria) this;
        }

        public Criteria andTriggerCodeNotEqualTo(Integer value) {
            addCriterion("trigger_code <>", value, "triggerCode");
            return (Criteria) this;
        }

        public Criteria andTriggerCodeGreaterThan(Integer value) {
            addCriterion("trigger_code >", value, "triggerCode");
            return (Criteria) this;
        }

        public Criteria andTriggerCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("trigger_code >=", value, "triggerCode");
            return (Criteria) this;
        }

        public Criteria andTriggerCodeLessThan(Integer value) {
            addCriterion("trigger_code <", value, "triggerCode");
            return (Criteria) this;
        }

        public Criteria andTriggerCodeLessThanOrEqualTo(Integer value) {
            addCriterion("trigger_code <=", value, "triggerCode");
            return (Criteria) this;
        }

        public Criteria andTriggerCodeIn(List<Integer> values) {
            addCriterion("trigger_code in", values, "triggerCode");
            return (Criteria) this;
        }

        public Criteria andTriggerCodeNotIn(List<Integer> values) {
            addCriterion("trigger_code not in", values, "triggerCode");
            return (Criteria) this;
        }

        public Criteria andTriggerCodeBetween(Integer value1, Integer value2) {
            addCriterion("trigger_code between", value1, value2, "triggerCode");
            return (Criteria) this;
        }

        public Criteria andTriggerCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("trigger_code not between", value1, value2, "triggerCode");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNull() {
            addCriterion("handle_time is null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNotNull() {
            addCriterion("handle_time is not null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeEqualTo(Long value) {
            addCriterion("handle_time =", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotEqualTo(Long value) {
            addCriterion("handle_time <>", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThan(Long value) {
            addCriterion("handle_time >", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("handle_time >=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThan(Long value) {
            addCriterion("handle_time <", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThanOrEqualTo(Long value) {
            addCriterion("handle_time <=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIn(List<Long> values) {
            addCriterion("handle_time in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotIn(List<Long> values) {
            addCriterion("handle_time not in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeBetween(Long value1, Long value2) {
            addCriterion("handle_time between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotBetween(Long value1, Long value2) {
            addCriterion("handle_time not between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleCodeIsNull() {
            addCriterion("handle_code is null");
            return (Criteria) this;
        }

        public Criteria andHandleCodeIsNotNull() {
            addCriterion("handle_code is not null");
            return (Criteria) this;
        }

        public Criteria andHandleCodeEqualTo(Integer value) {
            addCriterion("handle_code =", value, "handleCode");
            return (Criteria) this;
        }

        public Criteria andHandleCodeNotEqualTo(Integer value) {
            addCriterion("handle_code <>", value, "handleCode");
            return (Criteria) this;
        }

        public Criteria andHandleCodeGreaterThan(Integer value) {
            addCriterion("handle_code >", value, "handleCode");
            return (Criteria) this;
        }

        public Criteria andHandleCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("handle_code >=", value, "handleCode");
            return (Criteria) this;
        }

        public Criteria andHandleCodeLessThan(Integer value) {
            addCriterion("handle_code <", value, "handleCode");
            return (Criteria) this;
        }

        public Criteria andHandleCodeLessThanOrEqualTo(Integer value) {
            addCriterion("handle_code <=", value, "handleCode");
            return (Criteria) this;
        }

        public Criteria andHandleCodeIn(List<Integer> values) {
            addCriterion("handle_code in", values, "handleCode");
            return (Criteria) this;
        }

        public Criteria andHandleCodeNotIn(List<Integer> values) {
            addCriterion("handle_code not in", values, "handleCode");
            return (Criteria) this;
        }

        public Criteria andHandleCodeBetween(Integer value1, Integer value2) {
            addCriterion("handle_code between", value1, value2, "handleCode");
            return (Criteria) this;
        }

        public Criteria andHandleCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("handle_code not between", value1, value2, "handleCode");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusIsNull() {
            addCriterion("alarm_status is null");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusIsNotNull() {
            addCriterion("alarm_status is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusEqualTo(Integer value) {
            addCriterion("alarm_status =", value, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusNotEqualTo(Integer value) {
            addCriterion("alarm_status <>", value, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusGreaterThan(Integer value) {
            addCriterion("alarm_status >", value, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("alarm_status >=", value, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusLessThan(Integer value) {
            addCriterion("alarm_status <", value, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusLessThanOrEqualTo(Integer value) {
            addCriterion("alarm_status <=", value, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusIn(List<Integer> values) {
            addCriterion("alarm_status in", values, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusNotIn(List<Integer> values) {
            addCriterion("alarm_status not in", values, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusBetween(Integer value1, Integer value2) {
            addCriterion("alarm_status between", value1, value2, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("alarm_status not between", value1, value2, "alarmStatus");
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