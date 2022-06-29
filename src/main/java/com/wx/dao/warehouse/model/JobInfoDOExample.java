package com.wx.dao.warehouse.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobInfoDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer offset;

    private Integer limit;

    public JobInfoDOExample() {
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

        public Criteria andJobDescIsNull() {
            addCriterion("job_desc is null");
            return (Criteria) this;
        }

        public Criteria andJobDescIsNotNull() {
            addCriterion("job_desc is not null");
            return (Criteria) this;
        }

        public Criteria andJobDescEqualTo(String value) {
            addCriterion("job_desc =", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescNotEqualTo(String value) {
            addCriterion("job_desc <>", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescGreaterThan(String value) {
            addCriterion("job_desc >", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescGreaterThanOrEqualTo(String value) {
            addCriterion("job_desc >=", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescLessThan(String value) {
            addCriterion("job_desc <", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescLessThanOrEqualTo(String value) {
            addCriterion("job_desc <=", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescLike(String value) {
            addCriterion("job_desc like", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescNotLike(String value) {
            addCriterion("job_desc not like", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescIn(List<String> values) {
            addCriterion("job_desc in", values, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescNotIn(List<String> values) {
            addCriterion("job_desc not in", values, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescBetween(String value1, String value2) {
            addCriterion("job_desc between", value1, value2, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescNotBetween(String value1, String value2) {
            addCriterion("job_desc not between", value1, value2, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailIsNull() {
            addCriterion("alarm_email is null");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailIsNotNull() {
            addCriterion("alarm_email is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailEqualTo(String value) {
            addCriterion("alarm_email =", value, "alarmEmail");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailNotEqualTo(String value) {
            addCriterion("alarm_email <>", value, "alarmEmail");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailGreaterThan(String value) {
            addCriterion("alarm_email >", value, "alarmEmail");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailGreaterThanOrEqualTo(String value) {
            addCriterion("alarm_email >=", value, "alarmEmail");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailLessThan(String value) {
            addCriterion("alarm_email <", value, "alarmEmail");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailLessThanOrEqualTo(String value) {
            addCriterion("alarm_email <=", value, "alarmEmail");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailLike(String value) {
            addCriterion("alarm_email like", value, "alarmEmail");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailNotLike(String value) {
            addCriterion("alarm_email not like", value, "alarmEmail");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailIn(List<String> values) {
            addCriterion("alarm_email in", values, "alarmEmail");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailNotIn(List<String> values) {
            addCriterion("alarm_email not in", values, "alarmEmail");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailBetween(String value1, String value2) {
            addCriterion("alarm_email between", value1, value2, "alarmEmail");
            return (Criteria) this;
        }

        public Criteria andAlarmEmailNotBetween(String value1, String value2) {
            addCriterion("alarm_email not between", value1, value2, "alarmEmail");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeIsNull() {
            addCriterion("schedule_type is null");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeIsNotNull() {
            addCriterion("schedule_type is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeEqualTo(String value) {
            addCriterion("schedule_type =", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeNotEqualTo(String value) {
            addCriterion("schedule_type <>", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeGreaterThan(String value) {
            addCriterion("schedule_type >", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("schedule_type >=", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeLessThan(String value) {
            addCriterion("schedule_type <", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeLessThanOrEqualTo(String value) {
            addCriterion("schedule_type <=", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeLike(String value) {
            addCriterion("schedule_type like", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeNotLike(String value) {
            addCriterion("schedule_type not like", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeIn(List<String> values) {
            addCriterion("schedule_type in", values, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeNotIn(List<String> values) {
            addCriterion("schedule_type not in", values, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeBetween(String value1, String value2) {
            addCriterion("schedule_type between", value1, value2, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeNotBetween(String value1, String value2) {
            addCriterion("schedule_type not between", value1, value2, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigIsNull() {
            addCriterion("schedule_config is null");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigIsNotNull() {
            addCriterion("schedule_config is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigEqualTo(String value) {
            addCriterion("schedule_config =", value, "scheduleConfig");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigNotEqualTo(String value) {
            addCriterion("schedule_config <>", value, "scheduleConfig");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigGreaterThan(String value) {
            addCriterion("schedule_config >", value, "scheduleConfig");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigGreaterThanOrEqualTo(String value) {
            addCriterion("schedule_config >=", value, "scheduleConfig");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigLessThan(String value) {
            addCriterion("schedule_config <", value, "scheduleConfig");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigLessThanOrEqualTo(String value) {
            addCriterion("schedule_config <=", value, "scheduleConfig");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigLike(String value) {
            addCriterion("schedule_config like", value, "scheduleConfig");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigNotLike(String value) {
            addCriterion("schedule_config not like", value, "scheduleConfig");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigIn(List<String> values) {
            addCriterion("schedule_config in", values, "scheduleConfig");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigNotIn(List<String> values) {
            addCriterion("schedule_config not in", values, "scheduleConfig");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigBetween(String value1, String value2) {
            addCriterion("schedule_config between", value1, value2, "scheduleConfig");
            return (Criteria) this;
        }

        public Criteria andScheduleConfigNotBetween(String value1, String value2) {
            addCriterion("schedule_config not between", value1, value2, "scheduleConfig");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyIsNull() {
            addCriterion("executor_route_strategy is null");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyIsNotNull() {
            addCriterion("executor_route_strategy is not null");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyEqualTo(String value) {
            addCriterion("executor_route_strategy =", value, "executorRouteStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyNotEqualTo(String value) {
            addCriterion("executor_route_strategy <>", value, "executorRouteStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyGreaterThan(String value) {
            addCriterion("executor_route_strategy >", value, "executorRouteStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyGreaterThanOrEqualTo(String value) {
            addCriterion("executor_route_strategy >=", value, "executorRouteStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyLessThan(String value) {
            addCriterion("executor_route_strategy <", value, "executorRouteStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyLessThanOrEqualTo(String value) {
            addCriterion("executor_route_strategy <=", value, "executorRouteStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyLike(String value) {
            addCriterion("executor_route_strategy like", value, "executorRouteStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyNotLike(String value) {
            addCriterion("executor_route_strategy not like", value, "executorRouteStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyIn(List<String> values) {
            addCriterion("executor_route_strategy in", values, "executorRouteStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyNotIn(List<String> values) {
            addCriterion("executor_route_strategy not in", values, "executorRouteStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyBetween(String value1, String value2) {
            addCriterion("executor_route_strategy between", value1, value2, "executorRouteStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorRouteStrategyNotBetween(String value1, String value2) {
            addCriterion("executor_route_strategy not between", value1, value2, "executorRouteStrategy");
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

        public Criteria andExecutorBlockStrategyIsNull() {
            addCriterion("executor_block_strategy is null");
            return (Criteria) this;
        }

        public Criteria andExecutorBlockStrategyIsNotNull() {
            addCriterion("executor_block_strategy is not null");
            return (Criteria) this;
        }

        public Criteria andExecutorBlockStrategyEqualTo(String value) {
            addCriterion("executor_block_strategy =", value, "executorBlockStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorBlockStrategyNotEqualTo(String value) {
            addCriterion("executor_block_strategy <>", value, "executorBlockStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorBlockStrategyGreaterThan(String value) {
            addCriterion("executor_block_strategy >", value, "executorBlockStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorBlockStrategyGreaterThanOrEqualTo(String value) {
            addCriterion("executor_block_strategy >=", value, "executorBlockStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorBlockStrategyLessThan(String value) {
            addCriterion("executor_block_strategy <", value, "executorBlockStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorBlockStrategyLessThanOrEqualTo(String value) {
            addCriterion("executor_block_strategy <=", value, "executorBlockStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorBlockStrategyLike(String value) {
            addCriterion("executor_block_strategy like", value, "executorBlockStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorBlockStrategyNotLike(String value) {
            addCriterion("executor_block_strategy not like", value, "executorBlockStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorBlockStrategyIn(List<String> values) {
            addCriterion("executor_block_strategy in", values, "executorBlockStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorBlockStrategyNotIn(List<String> values) {
            addCriterion("executor_block_strategy not in", values, "executorBlockStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorBlockStrategyBetween(String value1, String value2) {
            addCriterion("executor_block_strategy between", value1, value2, "executorBlockStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorBlockStrategyNotBetween(String value1, String value2) {
            addCriterion("executor_block_strategy not between", value1, value2, "executorBlockStrategy");
            return (Criteria) this;
        }

        public Criteria andExecutorTimeoutIsNull() {
            addCriterion("executor_timeout is null");
            return (Criteria) this;
        }

        public Criteria andExecutorTimeoutIsNotNull() {
            addCriterion("executor_timeout is not null");
            return (Criteria) this;
        }

        public Criteria andExecutorTimeoutEqualTo(Integer value) {
            addCriterion("executor_timeout =", value, "executorTimeout");
            return (Criteria) this;
        }

        public Criteria andExecutorTimeoutNotEqualTo(Integer value) {
            addCriterion("executor_timeout <>", value, "executorTimeout");
            return (Criteria) this;
        }

        public Criteria andExecutorTimeoutGreaterThan(Integer value) {
            addCriterion("executor_timeout >", value, "executorTimeout");
            return (Criteria) this;
        }

        public Criteria andExecutorTimeoutGreaterThanOrEqualTo(Integer value) {
            addCriterion("executor_timeout >=", value, "executorTimeout");
            return (Criteria) this;
        }

        public Criteria andExecutorTimeoutLessThan(Integer value) {
            addCriterion("executor_timeout <", value, "executorTimeout");
            return (Criteria) this;
        }

        public Criteria andExecutorTimeoutLessThanOrEqualTo(Integer value) {
            addCriterion("executor_timeout <=", value, "executorTimeout");
            return (Criteria) this;
        }

        public Criteria andExecutorTimeoutIn(List<Integer> values) {
            addCriterion("executor_timeout in", values, "executorTimeout");
            return (Criteria) this;
        }

        public Criteria andExecutorTimeoutNotIn(List<Integer> values) {
            addCriterion("executor_timeout not in", values, "executorTimeout");
            return (Criteria) this;
        }

        public Criteria andExecutorTimeoutBetween(Integer value1, Integer value2) {
            addCriterion("executor_timeout between", value1, value2, "executorTimeout");
            return (Criteria) this;
        }

        public Criteria andExecutorTimeoutNotBetween(Integer value1, Integer value2) {
            addCriterion("executor_timeout not between", value1, value2, "executorTimeout");
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

        public Criteria andChildJobIdIsNull() {
            addCriterion("child_job_id is null");
            return (Criteria) this;
        }

        public Criteria andChildJobIdIsNotNull() {
            addCriterion("child_job_id is not null");
            return (Criteria) this;
        }

        public Criteria andChildJobIdEqualTo(String value) {
            addCriterion("child_job_id =", value, "childJobId");
            return (Criteria) this;
        }

        public Criteria andChildJobIdNotEqualTo(String value) {
            addCriterion("child_job_id <>", value, "childJobId");
            return (Criteria) this;
        }

        public Criteria andChildJobIdGreaterThan(String value) {
            addCriterion("child_job_id >", value, "childJobId");
            return (Criteria) this;
        }

        public Criteria andChildJobIdGreaterThanOrEqualTo(String value) {
            addCriterion("child_job_id >=", value, "childJobId");
            return (Criteria) this;
        }

        public Criteria andChildJobIdLessThan(String value) {
            addCriterion("child_job_id <", value, "childJobId");
            return (Criteria) this;
        }

        public Criteria andChildJobIdLessThanOrEqualTo(String value) {
            addCriterion("child_job_id <=", value, "childJobId");
            return (Criteria) this;
        }

        public Criteria andChildJobIdLike(String value) {
            addCriterion("child_job_id like", value, "childJobId");
            return (Criteria) this;
        }

        public Criteria andChildJobIdNotLike(String value) {
            addCriterion("child_job_id not like", value, "childJobId");
            return (Criteria) this;
        }

        public Criteria andChildJobIdIn(List<String> values) {
            addCriterion("child_job_id in", values, "childJobId");
            return (Criteria) this;
        }

        public Criteria andChildJobIdNotIn(List<String> values) {
            addCriterion("child_job_id not in", values, "childJobId");
            return (Criteria) this;
        }

        public Criteria andChildJobIdBetween(String value1, String value2) {
            addCriterion("child_job_id between", value1, value2, "childJobId");
            return (Criteria) this;
        }

        public Criteria andChildJobIdNotBetween(String value1, String value2) {
            addCriterion("child_job_id not between", value1, value2, "childJobId");
            return (Criteria) this;
        }

        public Criteria andTriggerStatusIsNull() {
            addCriterion("trigger_status is null");
            return (Criteria) this;
        }

        public Criteria andTriggerStatusIsNotNull() {
            addCriterion("trigger_status is not null");
            return (Criteria) this;
        }

        public Criteria andTriggerStatusEqualTo(Integer value) {
            addCriterion("trigger_status =", value, "triggerStatus");
            return (Criteria) this;
        }

        public Criteria andTriggerStatusNotEqualTo(Integer value) {
            addCriterion("trigger_status <>", value, "triggerStatus");
            return (Criteria) this;
        }

        public Criteria andTriggerStatusGreaterThan(Integer value) {
            addCriterion("trigger_status >", value, "triggerStatus");
            return (Criteria) this;
        }

        public Criteria andTriggerStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("trigger_status >=", value, "triggerStatus");
            return (Criteria) this;
        }

        public Criteria andTriggerStatusLessThan(Integer value) {
            addCriterion("trigger_status <", value, "triggerStatus");
            return (Criteria) this;
        }

        public Criteria andTriggerStatusLessThanOrEqualTo(Integer value) {
            addCriterion("trigger_status <=", value, "triggerStatus");
            return (Criteria) this;
        }

        public Criteria andTriggerStatusIn(List<Integer> values) {
            addCriterion("trigger_status in", values, "triggerStatus");
            return (Criteria) this;
        }

        public Criteria andTriggerStatusNotIn(List<Integer> values) {
            addCriterion("trigger_status not in", values, "triggerStatus");
            return (Criteria) this;
        }

        public Criteria andTriggerStatusBetween(Integer value1, Integer value2) {
            addCriterion("trigger_status between", value1, value2, "triggerStatus");
            return (Criteria) this;
        }

        public Criteria andTriggerStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("trigger_status not between", value1, value2, "triggerStatus");
            return (Criteria) this;
        }

        public Criteria andTriggerLastTimeIsNull() {
            addCriterion("trigger_last_time is null");
            return (Criteria) this;
        }

        public Criteria andTriggerLastTimeIsNotNull() {
            addCriterion("trigger_last_time is not null");
            return (Criteria) this;
        }

        public Criteria andTriggerLastTimeEqualTo(Long value) {
            addCriterion("trigger_last_time =", value, "triggerLastTime");
            return (Criteria) this;
        }

        public Criteria andTriggerLastTimeNotEqualTo(Long value) {
            addCriterion("trigger_last_time <>", value, "triggerLastTime");
            return (Criteria) this;
        }

        public Criteria andTriggerLastTimeGreaterThan(Long value) {
            addCriterion("trigger_last_time >", value, "triggerLastTime");
            return (Criteria) this;
        }

        public Criteria andTriggerLastTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("trigger_last_time >=", value, "triggerLastTime");
            return (Criteria) this;
        }

        public Criteria andTriggerLastTimeLessThan(Long value) {
            addCriterion("trigger_last_time <", value, "triggerLastTime");
            return (Criteria) this;
        }

        public Criteria andTriggerLastTimeLessThanOrEqualTo(Long value) {
            addCriterion("trigger_last_time <=", value, "triggerLastTime");
            return (Criteria) this;
        }

        public Criteria andTriggerLastTimeIn(List<Long> values) {
            addCriterion("trigger_last_time in", values, "triggerLastTime");
            return (Criteria) this;
        }

        public Criteria andTriggerLastTimeNotIn(List<Long> values) {
            addCriterion("trigger_last_time not in", values, "triggerLastTime");
            return (Criteria) this;
        }

        public Criteria andTriggerLastTimeBetween(Long value1, Long value2) {
            addCriterion("trigger_last_time between", value1, value2, "triggerLastTime");
            return (Criteria) this;
        }

        public Criteria andTriggerLastTimeNotBetween(Long value1, Long value2) {
            addCriterion("trigger_last_time not between", value1, value2, "triggerLastTime");
            return (Criteria) this;
        }

        public Criteria andTriggerNextTimeIsNull() {
            addCriterion("trigger_next_time is null");
            return (Criteria) this;
        }

        public Criteria andTriggerNextTimeIsNotNull() {
            addCriterion("trigger_next_time is not null");
            return (Criteria) this;
        }

        public Criteria andTriggerNextTimeEqualTo(Long value) {
            addCriterion("trigger_next_time =", value, "triggerNextTime");
            return (Criteria) this;
        }

        public Criteria andTriggerNextTimeNotEqualTo(Long value) {
            addCriterion("trigger_next_time <>", value, "triggerNextTime");
            return (Criteria) this;
        }

        public Criteria andTriggerNextTimeGreaterThan(Long value) {
            addCriterion("trigger_next_time >", value, "triggerNextTime");
            return (Criteria) this;
        }

        public Criteria andTriggerNextTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("trigger_next_time >=", value, "triggerNextTime");
            return (Criteria) this;
        }

        public Criteria andTriggerNextTimeLessThan(Long value) {
            addCriterion("trigger_next_time <", value, "triggerNextTime");
            return (Criteria) this;
        }

        public Criteria andTriggerNextTimeLessThanOrEqualTo(Long value) {
            addCriterion("trigger_next_time <=", value, "triggerNextTime");
            return (Criteria) this;
        }

        public Criteria andTriggerNextTimeIn(List<Long> values) {
            addCriterion("trigger_next_time in", values, "triggerNextTime");
            return (Criteria) this;
        }

        public Criteria andTriggerNextTimeNotIn(List<Long> values) {
            addCriterion("trigger_next_time not in", values, "triggerNextTime");
            return (Criteria) this;
        }

        public Criteria andTriggerNextTimeBetween(Long value1, Long value2) {
            addCriterion("trigger_next_time between", value1, value2, "triggerNextTime");
            return (Criteria) this;
        }

        public Criteria andTriggerNextTimeNotBetween(Long value1, Long value2) {
            addCriterion("trigger_next_time not between", value1, value2, "triggerNextTime");
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