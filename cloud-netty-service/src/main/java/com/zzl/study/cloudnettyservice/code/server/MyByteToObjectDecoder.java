package com.zzl.study.cloudnettyservice.code.server;

import com.zzl.study.cloudnettyservice.code.pojo.User;
import com.zzl.study.cloudnettyservice.code.util.ProtostuffUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName MyByteToObjectDecoder
 * @Desc 自定义解码器
 * @Author Lenovo
 * @Date 2022/6/15 20:11
 * @Version 1.0
 **/
public class MyByteToObjectDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] b = new byte[in.readableBytes()];
        in.readBytes(b);
        User user = ProtostuffUtil.deserializer(b, User.class);
        System.out.println("自定义解码器，收到数据:"+user);
    }
}
