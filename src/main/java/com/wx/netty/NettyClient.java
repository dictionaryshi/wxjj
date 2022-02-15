package com.wx.netty;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.scy.core.format.MessageUtil;
import com.scy.core.format.NumberUtil;
import com.scy.netty.client.handler.ClientHandlers;
import com.scy.netty.client.handler.HeartBeatTimerHandler;
import com.scy.netty.handler.CodeHandler;
import com.scy.netty.handler.NettyIdleStateHandler;
import com.scy.netty.protocol.DecodeSpliter;
import com.scy.netty.util.SessionUtil;
import com.wx.netty.client.console.ConsoleCommandManager;
import com.wx.netty.client.console.LoginConsoleCommand;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author : shichunyang
 * Date    : 2022/1/5
 * Time    : 1:37 下午
 * ---------------------------------------
 * Desc    :
 */
@Slf4j
public class NettyClient {

    private static final int MAX_RETRY = 3;


    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup workerGroup = new NioEventLoopGroup(new ThreadFactoryBuilder().setNameFormat("netty-client-thread-pool-%d").build());

        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel socketChannel) {
                        // 空闲检测
                        socketChannel.pipeline().addLast(new NettyIdleStateHandler());
                        socketChannel.pipeline().addLast(new DecodeSpliter());
                        socketChannel.pipeline().addLast(CodeHandler.INSTANCE);
                        socketChannel.pipeline().addLast(ClientHandlers.INSTANCE);
                        // 心跳定时器
                        socketChannel.pipeline().addLast(new HeartBeatTimerHandler());
                    }
                });

        // 建立连接
        connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);
    }

    public static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        try {
            ChannelFuture future = bootstrap.connect(host, port).sync();
            if (future.isSuccess()) {
                System.out.println("连接服务器成功!");
                Channel channel = future.channel();
                startConsoleThread(channel);
                return;
            } else {
                log.error(MessageUtil.format("netty client connect fail", "host", host, "port", port));
            }
        } catch (Exception e) {
            log.error(MessageUtil.format("netty client connect fail", e, "host", host, "port", port));
        }

        if (Objects.equals(retry, NumberUtil.ZERO.intValue())) {
            log.info(MessageUtil.format("netty client reconnect fail", "host", host, "port", port));
            bootstrap.config().group().shutdownGracefully();
            return;
        }

        int order = (MAX_RETRY - retry) + 1;
        log.info(MessageUtil.format("netty client reconnect", "host", host, "port", port, "order", order));

        // 重连间隔
        int delay = 1 << order;
        bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
    }

    private static void startConsoleThread(Channel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }
}
