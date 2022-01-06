package com.wx.netty.protocol;

import lombok.Data;

import static com.wx.netty.protocol.Command.MESSAGE_REQUEST;

@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public int getCommand() {
        return MESSAGE_REQUEST;
    }
}
