package com.wx.dao.warehouse.model;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MqMessageDO {
    /**
     * id
     */
    private Long id;

    /**
     * 主题
     */
    private String topic;

    /**
     * 分组
     */
    private String mqGroup;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 重试次数
     */
    private Integer retryCount;

    /**
     * 分片id
     */
    private Long shardingId;

    /**
     * 超时
     */
    private Integer timeout;

    /**
     * 生效时间
     */
    private Long effectTime;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 数据
     */
    private String data;

    /**
     * 日志
     */
    private String log;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public void setMqGroup(String mqGroup) {
        this.mqGroup = mqGroup == null ? null : mqGroup.trim();
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public void setShardingId(Long shardingId) {
        this.shardingId = shardingId;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setEffectTime(Long effectTime) {
        this.effectTime = effectTime;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public void setLog(String log) {
        this.log = log == null ? null : log.trim();
    }
}