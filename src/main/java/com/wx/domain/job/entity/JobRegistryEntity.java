package com.wx.domain.job.entity;

import com.scy.redis.util.RedisUtil;
import com.wx.constant.RedisKeyEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author : shichunyang
 * Date    : 2022/6/29
 * Time    : 1:32 下午
 * ---------------------------------------
 * Desc    : JobRegistryEntity
 */
@Getter
@Setter
@ToString
public class JobRegistryEntity {

    /**
     * id
     */
    private Long id;

    /**
     * app标识
     */
    private String appName;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    public String getLockKey() {
        return RedisUtil.getRedisKey(RedisKeyEnum.JOB_REGISTRY_LOCK.getRedisKeyPrefix(), String.valueOf(appName));
    }
}
