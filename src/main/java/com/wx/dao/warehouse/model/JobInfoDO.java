package com.wx.dao.warehouse.model;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JobInfoDO {
    /**
     * id
     */
    private Long id;

    /**
     * 执行器id
     */
    private Long jobGroupId;

    /**
     * 任务描述
     */
    private String jobDesc;

    /**
     * 告警邮件
     */
    private String alarmEmail;

    /**
     * 调度类型
     */
    private String scheduleType;

    /**
     * 调度配置
     */
    private String scheduleConfig;

    /**
     * 路由策略
     */
    private String executorRouteStrategy;

    /**
     * 执行器app
     */
    private String executorApp;

    /**
     * 执行器名称
     */
    private String executorHandler;

    /**
     * 执行器任务参数
     */
    private String executorParam;

    /**
     * 阻塞处理策略
     */
    private String executorBlockStrategy;

    /**
     * 任务超时
     */
    private Integer executorTimeout;

    /**
     * 失败重试次数
     */
    private Integer executorFailRetryCount;

    /**
     * 子任务id
     */
    private String childJobId;

    /**
     * 调度状态
     */
    private Integer triggerStatus;

    /**
     * 任务最后一次执行时间
     */
    private Long triggerLastTime;

    /**
     * 任务下次调度时间
     */
    private Long triggerNextTime;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    public void setId(Long id) {
        this.id = id;
    }

    public void setJobGroupId(Long jobGroupId) {
        this.jobGroupId = jobGroupId;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc == null ? null : jobDesc.trim();
    }

    public void setAlarmEmail(String alarmEmail) {
        this.alarmEmail = alarmEmail == null ? null : alarmEmail.trim();
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType == null ? null : scheduleType.trim();
    }

    public void setScheduleConfig(String scheduleConfig) {
        this.scheduleConfig = scheduleConfig == null ? null : scheduleConfig.trim();
    }

    public void setExecutorRouteStrategy(String executorRouteStrategy) {
        this.executorRouteStrategy = executorRouteStrategy == null ? null : executorRouteStrategy.trim();
    }

    public void setExecutorApp(String executorApp) {
        this.executorApp = executorApp == null ? null : executorApp.trim();
    }

    public void setExecutorHandler(String executorHandler) {
        this.executorHandler = executorHandler == null ? null : executorHandler.trim();
    }

    public void setExecutorParam(String executorParam) {
        this.executorParam = executorParam == null ? null : executorParam.trim();
    }

    public void setExecutorBlockStrategy(String executorBlockStrategy) {
        this.executorBlockStrategy = executorBlockStrategy == null ? null : executorBlockStrategy.trim();
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public void setExecutorFailRetryCount(Integer executorFailRetryCount) {
        this.executorFailRetryCount = executorFailRetryCount;
    }

    public void setChildJobId(String childJobId) {
        this.childJobId = childJobId == null ? null : childJobId.trim();
    }

    public void setTriggerStatus(Integer triggerStatus) {
        this.triggerStatus = triggerStatus;
    }

    public void setTriggerLastTime(Long triggerLastTime) {
        this.triggerLastTime = triggerLastTime;
    }

    public void setTriggerNextTime(Long triggerNextTime) {
        this.triggerNextTime = triggerNextTime;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}