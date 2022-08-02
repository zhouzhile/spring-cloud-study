package com.zzl.study.cloudnettyservice.code.client;

import com.zzl.study.cloudnettyservice.code.pojo.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName MyClientHandler
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/12 17:12
 * @Version 1.0
 **/
public class MyClientHandler extends SimpleChannelInboundHandler<User> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler 的MyClientHandler方法，发送数据");
        for (int i=0;i<500;i++){
            User user = new User();
            user.setId(100);
            user.setName("zzl");
            ctx.writeAndFlush(user);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, User msg) throws Exception {
        System.out.println("");
    }
}
