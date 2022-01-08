package com.wx.netty.protocol;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.wx.netty.protocol.Command.LOGIN_REQUEST;

@Getter
@Setter
@ToString
public class LoginRequestPacket extends Packet {

    private String userName;

    private String password;

    @Override
    public int getCommand() {
        return LOGIN_REQUEST;
    }
}
