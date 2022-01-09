package com.wx.netty.server;

import com.wx.netty.attribute.SessionUtil;
import com.wx.netty.protocol.QuitGroupRequestPacket;
import com.wx.netty.protocol.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket) {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
        String groupId = requestPacket.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        // 2. 构造退群响应发送给客户端
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
        responsePacket.setGroupId(requestPacket.getGroupId());
        responsePacket.setSuccess(Boolean.TRUE);

        ctx.channel().writeAndFlush(responsePacket);
    }
}
