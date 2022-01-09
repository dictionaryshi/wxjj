package com.wx.netty.protocol;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.wx.netty.protocol.Command.JOIN_GROUP_RESPONSE;

@Getter
@Setter
@ToString
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public int getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
