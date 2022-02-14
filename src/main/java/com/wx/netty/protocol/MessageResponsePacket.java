package com.wx.netty.protocol;

import com.scy.netty.protocol.AbstractPacket;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.wx.netty.protocol.Command.MESSAGE_RESPONSE;

@Getter
@Setter
@ToString
public class MessageResponsePacket extends AbstractPacket {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public int getCommand() {
        return MESSAGE_RESPONSE;
    }
}
