package com.wx.netty.client;

import com.scy.netty.constant.NettyConstant;
import com.scy.netty.util.NettyUtil;
import com.wx.netty.protocol.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class HeartBeatResponseHandler extends SimpleChannelInboundHandler<HeartBeatResponsePacket> {

    public static final HeartBeatResponseHandler INSTANCE = new HeartBeatResponseHandler();

    private HeartBeatResponseHandler() {
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, HeartBeatResponsePacket heartBeatResponsePacket) {
        //System.out.println("收到服务端心跳响应");
        NettyUtil.setAttr(ctx.channel(), NettyConstant.LAST_READ_TIME, System.currentTimeMillis());
    }
}
