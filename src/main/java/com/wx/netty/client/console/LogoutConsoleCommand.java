package com.wx.netty.client.console;

import com.scy.netty.model.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LogoutConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();

        channel.writeAndFlush(logoutRequestPacket);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
