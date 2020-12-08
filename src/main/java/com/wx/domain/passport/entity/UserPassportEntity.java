package com.wx.domain.passport.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * UserPassportEntity
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
@Getter
@Setter
@ToString
public class UserPassportEntity {

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

    /**
     * 登陆token
     */
    private String token;
}
