package com.wx.netty.client.console;

import com.scy.netty.model.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入用户名登录: ");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setClientIp(scanner.next());
        loginRequestPacket.setTimestamp(System.currentTimeMillis());
        loginRequestPacket.setToken(null);

        if (!channel.isActive()) {
            return;
        }

        channel.writeAndFlush(loginRequestPacket);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
