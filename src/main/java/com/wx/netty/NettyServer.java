package com.wx.netty;

import com.wx.netty.server.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

/**
 * @author : shichunyang
 * Date    : 2022/1/5
 * Time    : 10:35 上午
 * ---------------------------------------
 * Desc    :
 */
public class NettyServer {

    private static final int BEGIN_PORT = 8000;

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        AttributeKey<String> attr = AttributeKey.newInstance("attr");

        AttributeKey<String> childAttr = AttributeKey.newInstance("childAttr");

        serverBootstrap

                .group(boosGroup, workerGroup)

                .channel(NioServerSocketChannel.class)

                .attr(attr, "attrValue")

                .childAttr(childAttr, "childAttrValue")

                //如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                .option(ChannelOption.SO_BACKLOG, 1024)

                // 是否开启TCP底层心跳机制，true为开启
                .childOption(ChannelOption.SO_KEEPALIVE, true)

                // 实时发送数据
                .childOption(ChannelOption.TCP_NODELAY, true)

                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    public void initChannel(NioServerSocketChannel nioServerSocketChannel) {
                        System.out.println("attr:" + nioServerSocketChannel.attr(attr).get());
                        System.out.println("服务端启动中");
                    }
                })

                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    public void initChannel(NioSocketChannel nioSocketChannel) {
                        System.out.println("childAttr:" + nioSocketChannel.attr(childAttr).get());
                        nioSocketChannel.pipeline().addLast(new ServerHandler());
                    }
                });


        bind(serverBootstrap, BEGIN_PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
