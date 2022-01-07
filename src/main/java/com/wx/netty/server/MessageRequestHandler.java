package com.wx.netty.server;

import com.wx.netty.protocol.MessageRequestPacket;
import com.wx.netty.protocol.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author : shichunyang
 * Date    : 2022/1/7
 * Time    : 9:58 上午
 * ---------------------------------------
 * Desc    :
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");

        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
