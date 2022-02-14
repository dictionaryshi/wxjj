package com.wx.netty.protocol;

import com.scy.netty.protocol.AbstractPacket;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.wx.netty.protocol.Command.MESSAGE_REQUEST;

@Getter
@Setter
@ToString
public class MessageRequestPacket extends AbstractPacket {

    private String toUserId;

    private String message;

    public MessageRequestPacket() {
    }

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public int getCommand() {
        return MESSAGE_REQUEST;
    }
}
