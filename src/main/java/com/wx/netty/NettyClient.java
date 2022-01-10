package com.wx.netty;

import com.wx.netty.attribute.SessionUtil;
import com.wx.netty.client.*;
import com.wx.netty.client.console.ConsoleCommandManager;
import com.wx.netty.client.console.LoginConsoleCommand;
import com.wx.netty.codec.PacketDecoder;
import com.wx.netty.codec.PacketEncoder;
import com.wx.netty.codec.Spliter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author : shichunyang
 * Date    : 2022/1/5
 * Time    : 1:37 下午
 * ---------------------------------------
 * Desc    :
 */
public class NettyClient {

    private static final int MAX_RETRY = 3;


    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        bootstrap
                // 指定线程模型
                .group(workerGroup)

                // 指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)

                // 设置TCP底层属性
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 500)

                .option(ChannelOption.SO_KEEPALIVE, true)

                .option(ChannelOption.TCP_NODELAY, true)

                // IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new Spliter());
                        socketChannel.pipeline().addLast(new PacketDecoder());
                        // 登录响应处理器
                        socketChannel.pipeline().addLast(new LoginResponseHandler());
                        // 收消息处理器
                        socketChannel.pipeline().addLast(new MessageResponseHandler());
                        // 创建群响应处理器
                        socketChannel.pipeline().addLast(new CreateGroupResponseHandler());
                        // 加群响应处理器
                        socketChannel.pipeline().addLast(new JoinGroupResponseHandler());
                        // 退群响应处理器
                        socketChannel.pipeline().addLast(new QuitGroupResponseHandler());
                        // 获取群成员响应处理器
                        socketChannel.pipeline().addLast(new ListGroupMembersResponseHandler());
                        // 群消息响应
                        socketChannel.pipeline().addLast(new GroupMessageResponseHandler());
                        // 登出响应处理器
                        socketChannel.pipeline().addLast(new LogoutResponseHandler());
                        socketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });

        // 建立连接
        connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接服务器成功!");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
                return;
            }

            if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
                return;
            }

            // 第几次重连
            int order = (MAX_RETRY - retry) + 1;
            System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");

            // 本次重连的间隔
            int delay = 1 << order;
            bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
        });
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
