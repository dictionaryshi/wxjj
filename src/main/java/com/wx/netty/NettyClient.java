package com.wx.netty;

import com.scy.netty.client.ClientConfig;
import com.scy.netty.util.SessionUtil;
import com.wx.netty.client.console.ConsoleCommandManager;
import com.wx.netty.client.console.LoginConsoleCommand;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

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

    public static void main(String[] args) throws Exception {
        ClientConfig clientConfig = new ClientConfig();

        com.scy.netty.client.NettyClient nettyClient = new com.scy.netty.client.NettyClient();
        nettyClient.init("127.0.0.1:8000", clientConfig);

        startConsoleThread(nettyClient.getChannel());

        TimeUnit.SECONDS.sleep(30);

        clientConfig.stop();
    }

    private static void startConsoleThread(Channel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (channel.isActive()) {
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }
}
