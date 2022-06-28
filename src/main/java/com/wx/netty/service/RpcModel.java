package com.wx.netty.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : shichunyang
 * Date    : 2022/6/28
 * Time    : 10:53 上午
 * ---------------------------------------
 * Desc    : RpcModel
 */
@Getter
@Setter
@ToString
public class RpcModel implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 账号
     */
    private String passport;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createdAt;
}
