package com.wx.netty.protocol;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.wx.netty.protocol.Command.LIST_GROUP_MEMBERS_REQUEST;

@Getter
@Setter
@ToString
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public int getCommand() {
        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
