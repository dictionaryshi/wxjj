package com.wx.netty.server;

import com.scy.core.StringUtil;
import com.wx.netty.attribute.SessionUtil;
import com.wx.netty.protocol.MessageRequestPacket;
import com.wx.netty.protocol.MessageResponsePacket;
import com.wx.netty.session.Session;
import com.wx.netty.util.NettyUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Objects;

/**
 * @author : shichunyang
 * Date    : 2022/1/7
 * Time    : 9:58 上午
 * ---------------------------------------
 * Desc    :
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    private MessageRequestHandler() {
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        // 消息发送方的session
        Session session = SessionUtil.getSession(ctx.channel());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(Objects.isNull(session) ? StringUtil.EMPTY : session.getUserId());
        messageResponsePacket.setFromUserName(Objects.isNull(session) ? StringUtil.EMPTY : session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        // 消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        // 将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            NettyUtil.sendMsg(ctx.channel(), toUserChannel, messageResponsePacket);
        } else {
            System.err.println("[" + messageRequestPacket.getToUserId() + "] 不在线，发送失败!");
        }
    }
}
