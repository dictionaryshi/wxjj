package com.wx.netty.protocol;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.wx.netty.protocol.Command.MESSAGE_REQUEST;

@Getter
@Setter
@ToString
public class MessageRequestPacket extends Packet {

    private String message;

    public MessageRequestPacket() {
    }

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public int getCommand() {
        return MESSAGE_REQUEST;
    }
}
