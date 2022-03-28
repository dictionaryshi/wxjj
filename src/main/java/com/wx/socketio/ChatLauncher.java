package com.wx.socketio;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.handler.SuccessAuthorizationListener;
import com.corundumstudio.socketio.listener.*;
import io.netty.handler.codec.http.HttpHeaders;

import java.net.SocketAddress;

public class ChatLauncher {

    public static void main(String[] args) throws InterruptedException {

        Configuration config = new Configuration();
        config.setExceptionListener(new DefaultExceptionListener());
        config.setPingInterval(60000);
        config.setHostname("127.0.0.1");
        config.setPort(9092);
        config.setAuthorizationListener(new SuccessAuthorizationListener());
        config.setAckMode(AckMode.AUTO_SUCCESS_ONLY);
        config.setMaxHttpContentLength(5 * 1024 * 1024);
        config.setMaxFramePayloadLength(5 * 1024 * 1024);

        config.getSocketConfig().setTcpNoDelay(Boolean.TRUE);
        config.getSocketConfig().setTcpKeepAlive(Boolean.TRUE);
        config.getSocketConfig().setReuseAddress(Boolean.TRUE);
        config.getSocketConfig().setAcceptBackLog(511);

        final SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {

            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("onConnect_" + client.toString());
            }
        });

        server.addDisconnectListener(new DisconnectListener() {

            @Override
            public void onDisconnect(SocketIOClient client) {
                System.out.println("onDisconnect_" + client.toString());
            }
        });

        server.addEventListener("chatRequest", ChatObject.class, new DataListener<ChatObject>() {

            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) {
                HandshakeData handshakeData = client.getHandshakeData();
                HttpHeaders httpHeaders = handshakeData.getHttpHeaders();
                SocketAddress remoteAddress = client.getRemoteAddress();
                boolean channelOpen = client.isChannelOpen();
                client.sendEvent("chatResponse", data);
            }
        });

        final SocketIONamespace chat1namespace = server.addNamespace("/chat1");
        chat1namespace.addEventListener("messageRequest", ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) {
                client.sendEvent("messageResponse", data);
            }
        });

        final SocketIONamespace chat2namespace = server.addNamespace("/chat2");
        chat2namespace.addEventListener("messageRequest", ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) {
                client.sendEvent("messageResponse", data);
            }
        });

        server.addEventListener("imgRequest", byte[].class, new DataListener<byte[]>() {

            @Override
            public void onData(SocketIOClient client, byte[] data, AckRequest ackRequest) {
                client.sendEvent("imgResponse", data);
            }
        });

        server.addEventListener("ackevent1", ChatObject.class, new DataListener<ChatObject>() {

            @Override
            public void onData(final SocketIOClient client, ChatObject data, final AckRequest ackRequest) {
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
            }
        });

        server.start();

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();
    }
}
