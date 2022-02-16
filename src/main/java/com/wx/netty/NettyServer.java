package com.wx.netty;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.scy.netty.handler.CodeHandler;
import com.scy.netty.handler.ExceptionHandler;
import com.scy.netty.handler.NettyIdleStateHandler;
import com.scy.netty.protocol.DecodeSpliter;
import com.scy.netty.server.handler.HeartBeatRequestHandler;
import com.scy.netty.server.handler.LoginRequestHandler;
import com.scy.netty.server.handler.PermissionAuditHandler;
import com.scy.netty.server.handler.ServerHandlers;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.TimeUnit;

/**
 * @author : shichunyang
 * Date    : 2022/1/5
 * Time    : 10:35 上午
 * ---------------------------------------
 * Desc    :
 */
public class NettyServer {

    private static final int BEGIN_PORT = 8000;

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup(new ThreadFactoryBuilder().setNameFormat("boosGroup-thread-pool-%d").build());
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(new ThreadFactoryBuilder().setNameFormat("workerGroup-thread-pool-%d").build());

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 511)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_REUSEADDR, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    public void initChannel(NioSocketChannel nioSocketChannel) {
                        // 空闲检测
                        nioSocketChannel.pipeline().addLast(new NettyIdleStateHandler());
                        nioSocketChannel.pipeline().addLast(new DecodeSpliter());
                        nioSocketChannel.pipeline().addLast(CodeHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast(PermissionAuditHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast(ServerHandlers.INSTANCE);
                        nioSocketChannel.pipeline().addLast(ExceptionHandler.INSTANCE);
                    }
                });

        bind(serverBootstrap, BEGIN_PORT);
    }

    public static void bind(ServerBootstrap serverBootstrap, int port) {
        serverBootstrap.bind(port).addListener((GenericFutureListener<ChannelFuture>) future -> {
            if (future.isSuccess()) {
                System.out.println("success");
            } else {
                System.out.println("fail");
                future.channel().eventLoop().schedule(() -> bind(serverBootstrap, port), 5, TimeUnit.SECONDS);
            }
        });
    }
}
