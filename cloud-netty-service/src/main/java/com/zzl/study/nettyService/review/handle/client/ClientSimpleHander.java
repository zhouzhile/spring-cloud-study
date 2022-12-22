package com.zzl.study.nettyService.review.handle.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @ClassName ClientSimpleHander
 * @Desc 最基本的处理器
 * @Author Lenovo
 * @Date 2022/12/22 14:51
 * @Version 1.0
 **/
public class ClientSimpleHander extends ChannelInboundHandlerAdapter {

    /**
     * 当通道就绪，就会触发这个方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       // 循环向服务端发送数
        for (int i= 0;i<500;i++){
            String msg = "周智乐";
            // 将消息转换成ByteBuf，再进行发送
            ctx.writeAndFlush(Unpooled.copiedBuffer("周智乐", CharsetUtil.UTF_8));
        }
        System.out.println("客户端向服务端发送了500条数据");
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
}
