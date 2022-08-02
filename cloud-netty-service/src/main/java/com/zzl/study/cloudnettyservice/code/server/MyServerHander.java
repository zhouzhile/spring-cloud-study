package com.zzl.study.cloudnettyservice.code.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName MyServerHander
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/12 16:56
 * @Version 1.0
 **/
public class MyServerHander extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("服务端读取到客户端的消息:"+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
