package com.wx.netty.protocol;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.wx.netty.protocol.Command.LOGOUT_RESPONSE;

@Getter
@Setter
@ToString
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public int getCommand() {
        return LOGOUT_RESPONSE;
    }
}
