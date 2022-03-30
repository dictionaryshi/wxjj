package com.wx.socketio;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.*;
import com.scy.core.format.MessageUtil;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

@Slf4j
public class ChatLauncher {

    public static void main(String[] argss) throws InterruptedException {
        Configuration config = new Configuration();
        config.setExceptionListener(new DefaultExceptionListener());
        config.setPingInterval(60000);
        config.setHostname("127.0.0.1");
        config.setPort(9092);
        config.setAuthorizationListener(data -> {
            return Boolean.TRUE;
        });
        config.setAckMode(AckMode.AUTO_SUCCESS_ONLY);
        config.setMaxHttpContentLength(5 * 1024 * 1024);
        config.setMaxFramePayloadLength(5 * 1024 * 1024);

        config.getSocketConfig().setTcpNoDelay(Boolean.TRUE);
        config.getSocketConfig().setTcpKeepAlive(Boolean.TRUE);
        config.getSocketConfig().setReuseAddress(Boolean.TRUE);
        config.getSocketConfig().setAcceptBackLog(511);

        final SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(client -> {
            System.out.println("onConnect_" + client.toString());
        });

        server.addDisconnectListener(client -> {
            System.out.println("onDisconnect_" + client.toString());
        });

        server.addEventListener("chatRequest", ChatObject.class, (client, data, ackRequest) -> {
            HandshakeData handshakeData = client.getHandshakeData();
            HttpHeaders httpHeaders = handshakeData.getHttpHeaders();
            SocketAddress remoteAddress = client.getRemoteAddress();
            boolean channelOpen = client.isChannelOpen();
            client.sendEvent("chatResponse", data);
        });

        final SocketIONamespace chat1namespace = server.addNamespace("/chat1");
        chat1namespace.addEventListener("messageRequest", ChatObject.class, (client, data, ackRequest) -> client.sendEvent("messageResponse", data));

        final SocketIONamespace chat2namespace = server.addNamespace("/chat2");
        chat2namespace.addEventListener("messageRequest", ChatObject.class, (client, data, ackRequest) -> client.sendEvent("messageResponse", data));

        server.addEventListener("imgRequest", byte[].class, (client, data, ackRequest) -> client.sendEvent("imgResponse", data));

        server.addEventListener("ackevent1", ChatObject.class, (client, data, ackRequest) -> {
            if (ackRequest.isAckRequested()) {
                ackRequest.sendAckData("server ack1", "server ack2");
            }

            ChatObject ackChatObjectData = new ChatObject(data.getUserName(), "server ack data");
            client.sendEvent("ackevent2", new AckCallback<String>(String.class) {

                @Override
                public void onSuccess(String result) {
                    System.out.println("ack from client: " + client.getSessionId() + " data: " + result);
                }
            }, ackChatObjectData);

            ChatObject ackChatObjectData1 = new ChatObject(data.getUserName(), "server ack void");
            client.sendEvent("ackevent3", new VoidAckCallback() {

                @Override
                protected void onSuccess() {
                    System.out.println("ack from client: " + client.getSessionId());
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
