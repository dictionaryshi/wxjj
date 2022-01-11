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

    int LIST_GROUP_MEMBERS_REQUEST = 9;

    int LIST_GROUP_MEMBERS_RESPONSE = 10;

    int JOIN_GROUP_REQUEST = 11;

    int JOIN_GROUP_RESPONSE = 12;

    int QUIT_GROUP_REQUEST = 13;

    int QUIT_GROUP_RESPONSE = 14;

    int GROUP_MESSAGE_REQUEST = 15;

    int GROUP_MESSAGE_RESPONSE = 16;

    int HEARTBEAT_REQUEST = 17;

    int HEARTBEAT_RESPONSE = 18;
}
