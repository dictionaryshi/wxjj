package com.wx.netty.handler;

import com.scy.core.ObjectUtil;
import com.scy.core.format.DateUtil;
import com.scy.core.format.MessageUtil;
import com.wx.netty.attribute.Attributes;
import com.wx.netty.util.NettyUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class IMIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 15;

    public IMIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        long lastReadTime = ObjectUtil.obj2Long(NettyUtil.getAttr(ctx.channel(), Attributes.LAST_READ_TIME), 0L);
        long spacing = System.currentTimeMillis() - lastReadTime;
        log.info(MessageUtil.format("心跳channelIdle", "spacing", DateUtil.millisecond2Second(spacing)));

        ctx.channel().close();
    }
}
