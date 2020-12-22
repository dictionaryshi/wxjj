package com.wx.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author : shichunyang
 * Date    : 2020/12/22
 * Time    : 9:46 下午
 * ---------------------------------------
 * Desc    : 用户账号
 */
@Getter
@Setter
@ToString
public class UserPassportResponse {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 账号
     */
    private String passport;

    /**
     * 创建时间
     */
    private Date createdAt;
}
