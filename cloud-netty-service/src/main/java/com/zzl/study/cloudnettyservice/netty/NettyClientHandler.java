package com.zzl.study.cloudnettyservice.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @ClassName NettyClientServer
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/8 21:54
 * @Version 1.0
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当通道就像，就会触发该方法，向客户端写数据
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("客户端向服务端发送的数据", CharsetUtil.UTF_8));
    }


    /**
     * 读取客户端返回的数据
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("服务端返回的数据："+byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
