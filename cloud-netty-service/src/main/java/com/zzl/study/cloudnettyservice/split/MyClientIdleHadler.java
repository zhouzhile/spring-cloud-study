package com.zzl.study.cloudnettyservice.split;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName MyClientIdleHadler
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/16 7:51
 * @Version 1.0
 **/
public class MyClientIdleHadler extends SimpleChannelInboundHandler<String> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(" client received :" + msg);
        if (msg != null && msg.equals("idle close")) {
            System.out.println(" 服务端关闭连接，客户端也关闭");
            ctx.channel().closeFuture();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端-客户端失去连接，重连中...");

    }
}
