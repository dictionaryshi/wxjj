package com.wx.netty.client.console;

import com.scy.netty.model.rpc.RpcRequest;
import com.scy.netty.util.SessionUtil;
import com.wx.dao.warehouse.model.UserPassportDO;
import com.wx.netty.service.UserService;
import io.netty.channel.Channel;

import java.util.*;

public class ConsoleCommandManager implements ConsoleCommand {

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();

        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("已登陆用户输入命令：");

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
        try {
            channel.writeAndFlush(rpcRequest).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  获取第一个指令
        String command = scanner.next();

        if (!SessionUtil.hasLogin(channel)) {
            return;
        }

        if (!channel.isActive()) {
            return;
        }

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.err.println("无法识别[" + command + "]指令，请重新输入!");
        }
    }
}
