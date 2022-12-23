package com.zzl.study.nettyService.split;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName MyMessageEncoder
 * @Desc 编码器
 * @Author Lenovo
 * @Date 2022/6/15 21:17
 * @Version 1.0
 **/
public class MyMessageEncoder  extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        System.out.println("编码");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getBytes());
    }
}
