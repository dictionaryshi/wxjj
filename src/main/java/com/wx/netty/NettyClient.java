package com.wx.netty;

import com.scy.netty.client.ClientConfig;
import com.scy.netty.util.SessionUtil;
import com.wx.netty.client.console.ConsoleCommandManager;
import com.wx.netty.client.console.LoginConsoleCommand;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author : shichunyang
 * Date    : 2022/1/5
 * Time    : 1:37 下午
 * ---------------------------------------
 * Desc    :
 */
@Slf4j
public class NettyClient {

    public static void main(String[] args) throws Exception {
        com.scy.netty.client.NettyClient nettyClient = new com.scy.netty.client.NettyClient();
        nettyClient.init("127.0.0.1:8000", new ClientConfig());

        startConsoleThread(nettyClient);
    }

    private static void startConsoleThread(com.scy.netty.client.NettyClient nettyClient) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (nettyClient.getChannel().isActive()) {
                if (!SessionUtil.hasLogin(nettyClient.getChannel())) {
                    loginConsoleCommand.exec(scanner, nettyClient.getChannel());
                } else {
                    consoleCommandManager.exec(scanner, nettyClient.getChannel());
                }
            }
        }).start();
    }
}
