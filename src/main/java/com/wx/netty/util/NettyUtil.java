package com.wx.netty.util;

import com.scy.core.format.MessageUtil;
import com.wx.netty.attribute.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : shichunyang
 * Date    : 2022/1/13
 * Time    : 9:36 上午
 * ---------------------------------------
 * Desc    :
 */
@Slf4j
public class NettyUtil {

    private NettyUtil() {
    }

    public static void pushMsg(ChannelHandlerContext ctx, Object msg) {
        ChannelFuture channelFuture = ctx.writeAndFlush(msg);
        channelFuture.addListener(future -> {
            if (future.isDone()) {
                log.info(MessageUtil.format("server push", "msg", msg));
            }
        });
    }

    public static void sendMsg(Channel fromChannel, Channel toChannel, Object msg) {
        ChannelFuture channelFuture = toChannel.writeAndFlush(msg);
        channelFuture.addListener(future -> {
            if (future.isDone()) {
                log.info(MessageUtil.format("send msg",
                        "from", SessionUtil.getSession(fromChannel), "to", SessionUtil.getSession(toChannel), "msg", msg));
            }
        });
    }
}
