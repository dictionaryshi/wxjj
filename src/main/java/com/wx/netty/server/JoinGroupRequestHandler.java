package com.wx.netty.server;

import com.wx.netty.attribute.SessionUtil;
import com.wx.netty.protocol.JoinGroupRequestPacket;
import com.wx.netty.protocol.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket requestPacket) {
        // 1获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
        String groupId = requestPacket.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        // 2. 构造加群响应发送给客户端
        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        responsePacket.setSuccess(Boolean.TRUE);
        responsePacket.setGroupId(groupId);

        ctx.channel().writeAndFlush(responsePacket);
    }
}
