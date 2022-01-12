package com.wx.netty;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : shichunyang
 * Date    : 2022/1/12
 * Time    : 9:48 下午
 * ---------------------------------------
 * Desc    :
 */
@Slf4j
public class ShutDown {

    public static void shutDown(NioEventLoopGroup boosGroup, NioEventLoopGroup workerGroup) {
        boosGroup.shutdownGracefully().syncUninterruptibly();
        workerGroup.shutdownGracefully().syncUninterruptibly();
        log.info("Close cim server success!!!");
    }
}
