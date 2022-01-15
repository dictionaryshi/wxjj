package com.wx.netty.server;

import com.wx.netty.attribute.Attributes;
import com.wx.netty.protocol.HeartBeatRequestPacket;
import com.wx.netty.protocol.HeartBeatResponsePacket;
import com.wx.netty.util.NettyUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket requestPacket) {
        NettyUtil.setAttr(ctx.channel(), Attributes.LAST_READ_TIME, System.currentTimeMillis());
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
