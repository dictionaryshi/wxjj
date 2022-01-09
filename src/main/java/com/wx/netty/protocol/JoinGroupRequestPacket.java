package com.wx.netty.protocol;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.wx.netty.protocol.Command.JOIN_GROUP_REQUEST;

@Getter
@Setter
@ToString
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public int getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
