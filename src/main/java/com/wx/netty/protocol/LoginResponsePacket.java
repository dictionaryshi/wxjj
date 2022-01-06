package com.wx.netty.protocol;

import lombok.Data;

import static com.wx.netty.protocol.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public int getCommand() {
        return LOGIN_RESPONSE;
    }
}
