package com.wx.netty.protocol;

import com.scy.netty.protocol.AbstractPacket;

import static com.wx.netty.protocol.Command.HEARTBEAT_RESPONSE;

public class HeartBeatResponsePacket extends AbstractPacket {

    @Override
    public int getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
