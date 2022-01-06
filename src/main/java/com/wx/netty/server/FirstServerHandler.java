package com.wx.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author chao.yu
 * chao.yu@dianping.com
 * @date 2018/08/04 06:21.
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 这个方法在接收到客户端发来的数据之后被回调。
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));

        // 回复数据到客户端
        System.out.println(new Date() + ": 服务端写数据");
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);

//        try {
//            TimeUnit.MILLISECONDS.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println(new Date() + ": 服务端写数据(测试粘包)");
        ByteBuf data = getByteBuf(ctx);
        ctx.channel().writeAndFlush(data);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "来自服务端的响应数据！".getBytes(Charset.forName("utf-8"));

        //  ctx.alloc() 获取到一个 ByteBuf 的内存管理器，这个
        //
        //内存管理器的作用就是分配一个 ByteBuf，然后我们把字符串的二进制数据填充到 ByteBuf，这样我们就获取到了 Netty 需要的一个数据格式。
        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }
}
