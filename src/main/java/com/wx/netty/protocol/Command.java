package com.wx.netty.protocol;

public interface Command {

    int MESSAGE_REQUEST = 99;

    int MESSAGE_RESPONSE = 1010;

    int LOGOUT_REQUEST = 1111;

    int LOGOUT_RESPONSE = 1212;
}
