package com.wx.netty.protocol;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static com.wx.netty.protocol.Command.CREATE_GROUP_RESPONSE;

@Getter
@Setter
@ToString
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public int getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
