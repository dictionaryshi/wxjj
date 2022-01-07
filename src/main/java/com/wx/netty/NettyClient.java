package com.wx.netty;

import com.wx.netty.attribute.LoginUtil;
import com.wx.netty.client.ClientHandler;
import com.wx.netty.client.LoginResponseHandler;
import com.wx.netty.client.MessageResponseHandler;
import com.wx.netty.codec.PacketDecoder;
import com.wx.netty.codec.PacketEncoder;
import com.wx.netty.protocol.MessageRequestPacket;
import com.wx.netty.protocol.PacketCodeC;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

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

        AttributeKey<String> attr = AttributeKey.newInstance("attr");

        bootstrap
                // 指定线程模型
                .group(workerGroup)

                // 指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)

                // 绑定自定义属性到 channel
                .attr(attr, "attrValue")

                // 设置TCP底层属性
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 500)

                .option(ChannelOption.SO_KEEPALIVE, true)

                .option(ChannelOption.TCP_NODELAY, true)

                // IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel socketChannel) {
                        System.out.println("clientAttr:" + socketChannel.attr(attr).get());
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
                System.out.println("连接成功!");
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

            // 本次重连的间隔
            int delay = 1 << order;

            System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");

            bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    System.out.println("输入消息发送至服务端: ");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    channel.writeAndFlush(new MessageRequestPacket(line));
                }
            }
        }).start();
    }
}
