package com.wx.netty.server;

import com.scy.core.UUIDUtil;
import com.scy.netty.model.Session;
import com.scy.netty.util.NettyUtil;
import com.scy.netty.util.SessionUtil;
import com.wx.netty.protocol.LoginRequestPacket;
import com.wx.netty.protocol.LoginResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author : shichunyang
 * Date    : 2022/1/7
 * Time    : 9:53 上午
 * ---------------------------------------
 * Desc    :
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());

        NioSocketChannel channel = (NioSocketChannel) ctx.channel();
        InetSocketAddress localAddress = channel.localAddress();
        InetSocketAddress remoteAddress = channel.remoteAddress();

        if (valid(loginRequestPacket)) {
            String userId = UUIDUtil.uuid();

            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setSuccess(Boolean.TRUE);

            Session session = new Session();
            session.setUserId(userId);
            SessionUtil.bindSession(ctx.channel(), session);
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(Boolean.FALSE);
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录失败");
        }

        // 登录响应
        NettyUtil.pushMsg(ctx, loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        boolean flag = SessionUtil.unBindSession(ctx.channel());
        if (flag) {
            ctx.channel().close();
        }
    }
}
