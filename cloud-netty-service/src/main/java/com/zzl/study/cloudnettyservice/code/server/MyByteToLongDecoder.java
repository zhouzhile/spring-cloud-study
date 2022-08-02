package com.zzl.study.cloudnettyservice.code.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName MyByteToLongDecoder
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/12 16:52
 * @Version 1.0
 **/
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /**
     * 解码器
     *
     * @param ctx 上下文
     * @param in 入站的ByteBuf
     * @param out List集合，将解码后的数据传给下一个Handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() >= 8){
            out.add(in.readLong());
        }
    }
}
