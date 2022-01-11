package com.wx.netty.protocol;

import static com.wx.netty.protocol.Command.HEARTBEAT_RESPONSE;

public class HeartBeatResponsePacket extends Packet {

    @Override
    public int getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
