package com.wx.domain.job.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author : shichunyang
 * Date    : 2022/7/4
 * Time    : 4:30 下午
 * ---------------------------------------
 * Desc    : JobInfoEntity
 */
@Getter
@Setter
@ToString
public class JobInfoEntity {

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
}
