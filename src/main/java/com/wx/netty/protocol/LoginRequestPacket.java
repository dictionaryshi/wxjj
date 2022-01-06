package com.wx.netty.protocol;

import lombok.Data;

import static com.wx.netty.protocol.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet {

    private Integer userId;

    private String username;

    private String password;

    @Override
    public int getCommand() {
        return LOGIN_REQUEST;
    }
}
