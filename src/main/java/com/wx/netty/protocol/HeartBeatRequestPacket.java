package com.wx.netty.protocol;

import com.scy.netty.protocol.AbstractPacket;

import static com.wx.netty.protocol.Command.HEARTBEAT_REQUEST;

public class HeartBeatRequestPacket extends AbstractPacket {

    @Override
    public int getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
