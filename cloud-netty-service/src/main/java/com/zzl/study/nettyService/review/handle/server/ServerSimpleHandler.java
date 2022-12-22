package com.zzl.study.nettyService.review.handle.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @ClassName ServerSimpleHandler
 * @Desc 最基本的处理器
 * @Author Lenovo
 * @Date 2022/12/22 15:03
 * @Version 1.0
 **/
public class ServerSimpleHandler  extends ChannelInboundHandlerAdapter {

    /**
     * 接收客户端的数据
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("接收到客户端发送的消息:"+byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 数据读取完毕，回执消息给客户端
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端接收成功接收数据");
        ctx.writeAndFlush(Unpooled.copiedBuffer("客户端接收成功接收数据",CharsetUtil.UTF_8));
    }

    /**
     * 处理异常，并关闭通道
     *
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
