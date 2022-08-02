package com.zzl.study.cloudnettyservice.code.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName MyLontToByteEncoder
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/12 17:08
 * @Version 1.0
 **/
public class MyLontToByteEncoder extends MessageToByteEncoder<Long> {
    /**
     * 自定义解码器
     *
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {

        System.out.println("MyLontToByteEncoder的encode方法被调用，msg="+msg);
        out.writeLong(msg);
    }
}
