package com.zzl.study.cloudnettyservice.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @ClassName NettyServerHandler
 *
 * @Desc 自定义ChannelHandler,在这里进行消息接收，处理，发送
 * @Author Lenovo
 * @Date 2022/6/8 21:21
 * @Version 1.0
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取客户端发送的消息
     * ChannelHandlerContext ctx:上下文对象，包含管道Pipeline,通道channel
     * msg:客户端发送的消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("客户端发送的消息："+byteBuf.toString(CharsetUtil.UTF_8));
        // 将耗时的任务放入到TaskQueue中处理
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(10*1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("服务端向客户端发送的数据1",CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 将耗时的任务放入到TaskQueue中处理
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(20*1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("服务端向客户端发送的数据2",CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 数据读取完毕，回执消息给客户端
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        /**
         * 1.将数据写入到缓存并且刷新
         * 2.发送数据时，需要将数据进行编码
         */
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端收到数据，并读取完毕...",CharsetUtil.UTF_8));
        System.out.println("服务端回执消息完成");
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
