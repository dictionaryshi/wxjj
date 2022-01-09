package com.wx.netty.protocol;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static com.wx.netty.protocol.Command.CREATE_GROUP_REQUEST;

@Getter
@Setter
@ToString
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public int getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
