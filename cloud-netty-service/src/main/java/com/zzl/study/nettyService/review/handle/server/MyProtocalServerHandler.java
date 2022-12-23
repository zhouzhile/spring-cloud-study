package com.zzl.study.nettyService.review.handle.server;

import com.zzl.study.nettyService.review.protocal.MyProtocal;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @ClassName ServerMsgHander
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/12/22 22:09
 * @Version 1.0
 **/
public class MyProtocalServerHandler extends SimpleChannelInboundHandler<MyProtocal> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyProtocal msg) throws Exception {
        System.out.println(msg.getLen()+":"+new String(msg.getBytes(), CharsetUtil.UTF_8));
    }
}
