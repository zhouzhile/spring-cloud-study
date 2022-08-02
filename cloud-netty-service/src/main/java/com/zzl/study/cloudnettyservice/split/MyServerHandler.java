package com.zzl.study.cloudnettyservice.split;

import com.zzl.study.cloudnettyservice.code.pojo.User;
import com.zzl.study.cloudnettyservice.code.util.ProtostuffUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @ClassName MyServerHandler
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/15 20:51
 * @Version 1.0
 **/
public class MyServerHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        byte[] bytes = msg.getBytes();

        User user = ProtostuffUtil.deserializer(bytes, User.class);
        System.out.println("服务端收到数据:"+user);
    }
}
