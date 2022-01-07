package com.wx.netty.codec;

import com.wx.netty.protocol.Packet;
import com.wx.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author : shichunyang
 * Date    : 2022/1/7
 * Time    : 9:03 上午
 * ---------------------------------------
 * Desc    :
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    public void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode(out, packet);
    }
}
