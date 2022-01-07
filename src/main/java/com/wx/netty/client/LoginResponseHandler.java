package com.wx.netty.client;

import com.wx.netty.attribute.LoginUtil;
import com.wx.netty.protocol.LoginRequestPacket;
import com.wx.netty.protocol.LoginResponsePacket;
import com.wx.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @author : shichunyang
 * Date    : 2022/1/7
 * Time    : 8:54 上午
 * ---------------------------------------
 * Desc    :
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    /**
     * 这个方法会在客户端连接建立成功之后被调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("shichunyang");
        loginRequestPacket.setPassword("naodian12300");

        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.isSuccess()) {
            System.out.println(new Date() + ": 客户端登录成功");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭!");
        super.channelInactive(ctx);
    }
}
