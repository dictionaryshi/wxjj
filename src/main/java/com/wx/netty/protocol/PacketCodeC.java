package com.wx.netty.protocol;

import com.scy.core.net.HessianUtil;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private PacketCodeC() {
    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        // 序列化 java 对象
        byte[] bytes = HessianUtil.serialize(packet);

        // 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        if (Objects.equals(serializeAlgorithm, SerializerAlgorithm.JSON)) {
            return HessianUtil.deserialize(bytes);
        }

        return null;
    }
}
