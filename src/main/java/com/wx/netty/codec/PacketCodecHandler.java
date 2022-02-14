package com.wx.netty.codec;

import com.scy.netty.protocol.AbstractPacket;
import com.scy.netty.protocol.PacketCodeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, AbstractPacket> {

    private PacketCodecHandler() {
    }

    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) {
        out.add(PacketCodeUtil.decode(byteBuf));
    }

    @Override
    public void encode(ChannelHandlerContext ctx, AbstractPacket packet, List<Object> out) {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();

        PacketCodeUtil.encode(byteBuf, packet);

        out.add(byteBuf);
    }
}
