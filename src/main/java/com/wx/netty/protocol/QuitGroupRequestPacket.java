package com.wx.netty.protocol;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.wx.netty.protocol.Command.QUIT_GROUP_REQUEST;

@Getter
@Setter
@ToString
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public int getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}
