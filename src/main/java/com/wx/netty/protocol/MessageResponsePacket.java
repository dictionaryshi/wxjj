package com.wx.netty.protocol;

import lombok.Data;

import static com.wx.netty.protocol.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public int getCommand() {
        return MESSAGE_RESPONSE;
    }
}
