package com.wx.netty.server;

import com.scy.core.UUIDUtil;
import com.wx.netty.attribute.SessionUtil;
import com.wx.netty.protocol.LoginRequestPacket;
import com.wx.netty.protocol.LoginResponsePacket;
import com.wx.netty.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author : shichunyang
 * Date    : 2022/1/7
 * Time    : 9:53 上午
 * ---------------------------------------
 * Desc    :
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());

        if (valid(loginRequestPacket)) {
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录成功");

            String userId = UUIDUtil.uuid();

            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setSuccess(Boolean.TRUE);

            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(Boolean.FALSE);
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录失败");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
        super.channelInactive(ctx);
    }
}
