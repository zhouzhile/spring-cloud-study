package com.zzl.study.cloudnettyservice.split;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName MyMessageDecoder
 * @Desc 编码器
 * @Author Lenovo
 * @Date 2022/6/15 21:17
 * @Version 1.0
 **/
public class MyMessageEncoder  extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getBytes());
    }
}
