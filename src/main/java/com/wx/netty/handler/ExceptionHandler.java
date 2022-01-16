package com.wx.netty.handler;

import com.scy.core.format.MessageUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

/**
 * @author : shichunyang
 * Date    : 2022/1/16
 * Time    : 11:34 上午
 * ---------------------------------------
 * Desc    :
 */
@Slf4j
@ChannelHandler.Sharable
public class ExceptionHandler extends ChannelInboundHandlerAdapter {

    private static final Pattern IGNORABLE_ERROR_MESSAGE = Pattern.compile(
            "^.*(?:connection.*(?:reset|closed|abort|broken)|broken.*pipe).*$", Pattern.CASE_INSENSITIVE);

    public static final ExceptionHandler INSTANCE = new ExceptionHandler();

    private ExceptionHandler() {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String message = cause.getMessage();
        if (message != null && IGNORABLE_ERROR_MESSAGE.matcher(message).matches()) {
            log.info(message);
            return;
        }

        log.error(MessageUtil.format("exceptionCaught", cause));
    }
}
