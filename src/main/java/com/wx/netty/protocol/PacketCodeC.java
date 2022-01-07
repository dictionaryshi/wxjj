package com.wx.netty.protocol;

import com.fasterxml.jackson.core.type.TypeReference;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.wx.netty.protocol.Command.*;

public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private static final Map<Integer, TypeReference<? extends Packet>> packetTypeMap;

    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();

        packetTypeMap.put(LOGIN_REQUEST, new TypeReference<LoginRequestPacket>() {
        });
        packetTypeMap.put(LOGIN_RESPONSE, new TypeReference<LoginResponsePacket>() {
        });
        packetTypeMap.put(MESSAGE_REQUEST, new TypeReference<MessageRequestPacket>() {
        });
        packetTypeMap.put(MESSAGE_RESPONSE, new TypeReference<MessageResponsePacket>() {
        });

        serializerMap = new HashMap<>();

        serializerMap.put(Serializer.DEFAULT.getSerializerAlogrithm(), Serializer.DEFAULT);
    }

    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        // 2. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeInt(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        int command = byteBuf.readInt();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        TypeReference<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(bytes, requestType);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private TypeReference<? extends Packet> getRequestType(int command) {
        return packetTypeMap.get(command);
    }
}
