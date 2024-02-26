package com.wx.socketio;

import com.corundumstudio.socketio.*;
import com.scy.core.format.MessageUtil;
import com.scy.netty.model.Session;
import com.scy.netty.socketio.SocketSessionUtil;
import com.scy.netty.socketio.SocketioUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

@Slf4j
public class ChatLauncher {

    public static void main(String[] argss) throws InterruptedException {
        SocketIOServer server = SocketioUtil.getServer();

        server.addEventListener("chatRequest", ChatObject.class, (client, data, ackRequest) -> {
            SocketAddress remoteAddress = client.getRemoteAddress();
            boolean channelOpen = client.isChannelOpen();
            boolean writable = client.isWritable();
            client.sendEvent("chatResponse", data);
        });

        server.addEventListener("imgRequest", byte[].class, (client, data, ackRequest) -> client.sendEvent("imgResponse", data));

        server.addEventListener("ackevent1", ChatObject.class, (client, data, ackRequest) -> {
            if (ackRequest.isAckRequested()) {
                ackRequest.sendAckData("server ack1", "server ack2");
            }

            Session session = SocketSessionUtil.getSession(client);

            ChatObject ackChatObjectData = new ChatObject(data.getUserName(), "ackevent2 data");
            client.sendEvent("ackevent2", new AckCallback<String>(String.class) {
                @Override
                public void onSuccess(String result) {
                    log.info(MessageUtil.format("ack from client", "session", session, "result", result));
                }
            }, ackChatObjectData);

            ChatObject ackChatObjectData1 = new ChatObject(data.getUserName(), "ackevent3 data");
            client.sendEvent("ackevent3", new VoidAckCallback() {
                @Override
                protected void onSuccess() {
                    log.info(MessageUtil.format("ack from client", "session", session));
                }
            }, ackChatObjectData1);
        });

        server.addEventInterceptor((client, eventName, args, ackRequest) -> {
            log.info(MessageUtil.format("eventInterceptor", "eventName", eventName, "args", args));
        });

        server.start();

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();
    }
}
