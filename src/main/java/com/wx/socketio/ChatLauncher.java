package com.wx.socketio;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.*;
import com.scy.core.StringUtil;
import com.scy.core.format.MessageUtil;
import com.scy.netty.socketio.SocketCookieUtil;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

@Slf4j
public class ChatLauncher {

    public static void main(String[] argss) throws InterruptedException {
        Configuration config = new Configuration();
        config.setExceptionListener(new DefaultExceptionListener());
        config.setPingInterval(60000);
        config.setPort(9092);
        config.setAckMode(AckMode.AUTO_SUCCESS_ONLY);
        config.setMaxHttpContentLength(5 * 1024 * 1024);
        config.setMaxFramePayloadLength(5 * 1024 * 1024);

        // 设置是否禁用 Nagle 算法。如果设置为 true，表示禁用，可以减少数据包的延迟，适用于小包或需要低延迟的传输。通常，对于需要即时性的应用，建议设置为 true。
        config.getSocketConfig().setTcpNoDelay(Boolean.TRUE);
        // 设置是否启用 TCP 的 keepalive 属性。如果设置为 true，TCP 会定期发送探测包以检测连接是否仍然有效。这有助于在网络中断时快速发现死连接。通常，对于需要维持长连接的应用，建议设置为 true。
        config.getSocketConfig().setTcpKeepAlive(Boolean.TRUE);
        // 设置是否允许重用套接字地址。如果设置为 true，即使前一个连接还在 TIME_WAIT 状态，也允许新的套接字绑定相同的地址。这对于服务器应用来说很有用，可以快速重启绑定到相同端口。
        config.getSocketConfig().setReuseAddress(Boolean.TRUE);
        // 设置套接字接受连接请求的队列长度。如果队列满了，新的连接请求可能会被拒绝。常用值取决于应用的并发需求，但511是一个常见的设置，为了兼容性和性能。
        config.getSocketConfig().setAcceptBackLog(511);

        final SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(client -> {
            String token = SocketCookieUtil.getCookieValue(client.getHandshakeData(), "SCY_SSO");
            if (StringUtil.isEmpty(token)) {
                client.disconnect();
                return;
            }
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
