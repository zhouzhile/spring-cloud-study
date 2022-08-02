package com.zzl.study.cloudnettyservice.tuling;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName ChatClient
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/10 11:37
 * @Version 1.0
 **/
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("client received "+msg);
        if (msg != null && msg.equals("idle close")){
            System.out.println("服务端关闭连接，客户端也关闭");
            ctx.channel().close();
        }
    }
}
