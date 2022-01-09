package com.wx.netty.protocol;

import com.wx.netty.session.Session;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static com.wx.netty.protocol.Command.LIST_GROUP_MEMBERS_RESPONSE;

@Getter
@Setter
@ToString
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public int getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
