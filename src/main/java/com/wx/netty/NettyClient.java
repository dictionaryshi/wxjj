package com.wx.netty;

import com.wx.netty.attribute.SessionUtil;
import com.wx.netty.client.LoginResponseHandler;
import com.wx.netty.client.MessageResponseHandler;
import com.wx.netty.codec.PacketDecoder;
import com.wx.netty.codec.PacketEncoder;
import com.wx.netty.codec.Spliter;
import com.wx.netty.protocol.LoginRequestPacket;
import com.wx.netty.protocol.MessageRequestPacket;
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
                        socketChannel.pipeline().addLast(new LoginResponseHandler());
                        socketChannel.pipeline().addLast(new MessageResponseHandler());
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
        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    System.out.print("输入用户名登录: ");
                    String username = sc.nextLine();
                    loginRequestPacket.setUserName(username);

                    // 密码使用默认的
                    loginRequestPacket.setPassword("pwd");

                    // 发送登录数据包
                    channel.writeAndFlush(loginRequestPacket);

                    waitForLoginResponse();
                } else {
                    String toUserId = sc.next();
                    String message = sc.next();
                    channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
                }
            }
        }).start();
    }


    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
