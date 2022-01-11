package com.wx.netty.protocol;

import com.fasterxml.jackson.core.type.TypeReference;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.wx.netty.protocol.Command.*;

public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Integer, TypeReference<? extends Packet>> packetTypeMap;

    private final Map<Byte, Serializer> serializerMap;

    private PacketCodeC() {
        packetTypeMap = new HashMap<>();

        packetTypeMap.put(LOGIN_REQUEST, new TypeReference<LoginRequestPacket>() {
        });
        packetTypeMap.put(LOGIN_RESPONSE, new TypeReference<LoginResponsePacket>() {
        });
        packetTypeMap.put(MESSAGE_REQUEST, new TypeReference<MessageRequestPacket>() {
        });
        packetTypeMap.put(MESSAGE_RESPONSE, new TypeReference<MessageResponsePacket>() {
        });
        packetTypeMap.put(LOGOUT_REQUEST, new TypeReference<LogoutRequestPacket>() {
        });
        packetTypeMap.put(LOGOUT_RESPONSE, new TypeReference<LogoutResponsePacket>() {
        });
        packetTypeMap.put(CREATE_GROUP_REQUEST, new TypeReference<CreateGroupRequestPacket>() {
        });
        packetTypeMap.put(CREATE_GROUP_RESPONSE, new TypeReference<CreateGroupResponsePacket>() {
        });
        packetTypeMap.put(LIST_GROUP_MEMBERS_REQUEST, new TypeReference<ListGroupMembersRequestPacket>() {
        });
        packetTypeMap.put(LIST_GROUP_MEMBERS_RESPONSE, new TypeReference<ListGroupMembersResponsePacket>() {
        });
        packetTypeMap.put(JOIN_GROUP_REQUEST, new TypeReference<JoinGroupRequestPacket>() {
        });
        packetTypeMap.put(JOIN_GROUP_RESPONSE, new TypeReference<JoinGroupResponsePacket>() {
        });
        packetTypeMap.put(QUIT_GROUP_REQUEST, new TypeReference<QuitGroupRequestPacket>() {
        });
        packetTypeMap.put(QUIT_GROUP_RESPONSE, new TypeReference<QuitGroupResponsePacket>() {
        });
        packetTypeMap.put(GROUP_MESSAGE_REQUEST, new TypeReference<GroupMessageRequestPacket>() {
        });
        packetTypeMap.put(GROUP_MESSAGE_RESPONSE, new TypeReference<GroupMessageResponsePacket>() {
        });
        packetTypeMap.put(HEARTBEAT_REQUEST, new TypeReference<HeartBeatRequestPacket>() {
        });
        packetTypeMap.put(HEARTBEAT_RESPONSE, new TypeReference<HeartBeatResponsePacket>() {
        });

        serializerMap = new HashMap<>();

        serializerMap.put(Serializer.DEFAULT.getSerializerAlgorithm(), Serializer.DEFAULT);
    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        // 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeInt(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
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

        Serializer serializer = serializerMap.get(serializeAlgorithm);
        if (Objects.isNull(serializer)) {
            return null;
        }

        if (Objects.equals(serializeAlgorithm, SerializerAlgorithm.JSON)) {
            TypeReference<? extends Packet> typeReference = packetTypeMap.get(command);
            if (Objects.isNull(typeReference)) {
                return null;
            }

            return serializer.deserialize(bytes, typeReference);
        }

        return null;
    }
}
