package com.wx.netty.server;

import com.scy.netty.protocol.AbstractPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static com.wx.netty.protocol.Command.*;

@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<AbstractPacket> {

    public static final IMHandler INSTANCE = new IMHandler();

    private final Map<Integer, SimpleChannelInboundHandler<? extends AbstractPacket>> handlerMap;

    private IMHandler() {
        handlerMap = new HashMap<>();

        handlerMap.put(MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, AbstractPacket packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
