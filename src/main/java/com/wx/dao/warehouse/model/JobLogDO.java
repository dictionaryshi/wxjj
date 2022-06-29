package com.wx.dao.warehouse.model;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JobLogDO {
    /**
     * id
     */
    private Long id;

    /**
     * 执行器id
     */
    private Long jobGroupId;

    /**
     * job id
     */
    private Long jobId;

    /**
     * 执行器地址
     */
    private String executorAddress;

    /**
     * 执行器app
     */
    private String executorApp;

    /**
     * 执行器名称
     */
    private String executorHandler;

    /**
     * 执行器参数
     */
    private String executorParam;

    /**
     * 执行器任务分片参数
     */
    private String executorShardingParam;

    /**
     * 失败重试次数
     */
    private Integer executorFailRetryCount;

    /**
     * 调度时间
     */
    private Long triggerTime;

    /**
     * 调度结果
     */
    private Integer triggerCode;

    /**
     * 执行时间
     */
    private Long handleTime;

    /**
     * 执行状态
     */
    private Integer handleCode;

    /**
     * 告警状态
     */
    private Integer alarmStatus;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 调度日志
     */
    private String triggerMsg;

    /**
     * 执行日志
     */
    private String handleMsg;

    public void setId(Long id) {
        this.id = id;
    }

    public void setJobGroupId(Long jobGroupId) {
        this.jobGroupId = jobGroupId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public void setExecutorAddress(String executorAddress) {
        this.executorAddress = executorAddress == null ? null : executorAddress.trim();
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

    public void setExecutorShardingParam(String executorShardingParam) {
        this.executorShardingParam = executorShardingParam == null ? null : executorShardingParam.trim();
    }

    public void setExecutorFailRetryCount(Integer executorFailRetryCount) {
        this.executorFailRetryCount = executorFailRetryCount;
    }

    public void setTriggerTime(Long triggerTime) {
        this.triggerTime = triggerTime;
    }

    public void setTriggerCode(Integer triggerCode) {
        this.triggerCode = triggerCode;
    }

    public void setHandleTime(Long handleTime) {
        this.handleTime = handleTime;
    }

    public void setHandleCode(Integer handleCode) {
        this.handleCode = handleCode;
    }

    public void setAlarmStatus(Integer alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setTriggerMsg(String triggerMsg) {
        this.triggerMsg = triggerMsg == null ? null : triggerMsg.trim();
    }

    public void setHandleMsg(String handleMsg) {
        this.handleMsg = handleMsg == null ? null : handleMsg.trim();
    }
}