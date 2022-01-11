package com.wx.netty.protocol;

import static com.wx.netty.protocol.Command.HEARTBEAT_REQUEST;

public class HeartBeatRequestPacket extends Packet {

    @Override
    public int getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
