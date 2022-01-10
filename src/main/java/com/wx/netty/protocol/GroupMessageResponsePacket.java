package com.wx.netty.protocol;

import com.wx.netty.session.Session;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.wx.netty.protocol.Command.GROUP_MESSAGE_RESPONSE;

@Getter
@Setter
@ToString
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public int getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
