package com.wx.netty.protocol;

public interface Command {

    int LOGIN_REQUEST = 7;

    int LOGIN_RESPONSE = 8;

    int MESSAGE_REQUEST = 3;

    int MESSAGE_RESPONSE = 4;

    int LOGOUT_REQUEST = 5;

    int LOGOUT_RESPONSE = 6;
}
