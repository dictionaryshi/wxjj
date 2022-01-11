package com.wx.netty.server;

import com.wx.netty.attribute.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author : shichunyang
 * Date    : 2022/1/7
 * Time    : 4:45 下午
 * ---------------------------------------
 * Desc    :
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static final AuthHandler INSTANCE = new AuthHandler();

    private AuthHandler() {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            System.out.println("用户登陆校验未通过，关闭连接");
            ctx.channel().close();
        } else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }
}
