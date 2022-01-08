package com.wx.netty.session;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2022/1/8
 * Time    : 9:06 下午
 * ---------------------------------------
 * Desc    :
 */
@Getter
@Setter
@ToString
public class Session {

    private String userId;

    private String userName;

    public Session() {
    }

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
