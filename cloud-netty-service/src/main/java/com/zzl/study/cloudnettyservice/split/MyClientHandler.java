package com.zzl.study.cloudnettyservice.split;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName MyClientHandler
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/15 20:48
 * @Version 1.0
 **/
public class MyClientHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端发送数据:周智乐");
        for (int i =0;i<200;i++){
            ctx.writeAndFlush("周智乐");
        }
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
