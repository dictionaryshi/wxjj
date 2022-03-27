package com.wx.netty;

import com.scy.core.net.NetworkInterfaceUtil;
import com.scy.netty.client.AbstractConnectClient;
import com.scy.netty.client.ClientConfig;
import com.scy.netty.model.LoginRequestPacket;
import com.scy.netty.model.rpc.RpcRequest;
import com.scy.netty.util.SessionUtil;
import com.wx.dao.warehouse.model.UserPassportDO;
import com.wx.netty.client.console.ConsoleCommandManager;
import com.wx.netty.client.console.LoginConsoleCommand;
import com.wx.netty.service.UserService;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

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
        clientConfig.setConnectClientClass(com.scy.netty.client.NettyClient.class);

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setRequestId("1");
        rpcRequest.setCreateTime(System.currentTimeMillis());
        rpcRequest.setClassName(UserService.class.getName());
        rpcRequest.setMethodName("getUserPassport");
        rpcRequest.setParameterTypes(new Class[]{UserPassportDO.class});

        UserPassportDO userPassportDO = new UserPassportDO();
        userPassportDO.setId(66L);
        userPassportDO.setPassport("chunyang");
        userPassportDO.setPassword("naodian12300");
        userPassportDO.setName("春阳");
        userPassportDO.setCreatedAt(new Date());
        userPassportDO.setUpdatedAt(new Date());

        rpcRequest.setParameters(new Object[]{userPassportDO});
        rpcRequest.setVersion("1.0");
        String s = UUID.randomUUID().toString();
        System.out.println(s);
        rpcRequest.setTraceId(s);

        ChannelFuture channelFuture1 = AbstractConnectClient.asyncSend("127.0.0.1:7080", clientConfig, rpcRequest);
        channelFuture1.syncUninterruptibly();

        clientConfig.stop();
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
            scanner.close();
        }).start();
    }
}
