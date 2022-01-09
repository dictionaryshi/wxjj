package com.wx.netty.protocol;

public interface Command {

    int LOGIN_REQUEST = 1;

    int LOGIN_RESPONSE = 2;

    int MESSAGE_REQUEST = 3;

    int MESSAGE_RESPONSE = 4;

    int LOGOUT_REQUEST = 5;

    int LOGOUT_RESPONSE = 6;

    int CREATE_GROUP_REQUEST = 7;

    int CREATE_GROUP_RESPONSE = 8;
}
