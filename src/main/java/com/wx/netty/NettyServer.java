package com.wx.netty;

import com.scy.netty.server.ServerConfig;

/**
 * @author : shichunyang
 * Date    : 2022/1/5
 * Time    : 10:35 上午
 * ---------------------------------------
 * Desc    :
 */
public class NettyServer {

    public static void main(String[] args) {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(8000);

        com.scy.netty.server.NettyServer nettyServer = new com.scy.netty.server.NettyServer();
        nettyServer.start(serverConfig);
    }
}
