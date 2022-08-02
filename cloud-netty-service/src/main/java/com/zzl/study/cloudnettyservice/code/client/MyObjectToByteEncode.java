package com.zzl.study.cloudnettyservice.code.client;

import com.zzl.study.cloudnettyservice.code.pojo.User;
import com.zzl.study.cloudnettyservice.code.util.ProtostuffUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName MyObjectToByteEncode
 * @Desc 自定义编码器
 * @Author Lenovo
 * @Date 2022/6/15 14:40
 * @Version 1.0
 **/
public class MyObjectToByteEncode extends MessageToByteEncoder<User> {

    @Override
    protected void encode(ChannelHandlerContext ctx, User msg, ByteBuf out) throws Exception {
        out.writeBytes(ProtostuffUtil.serializer(msg));
        System.out.println("客户端使用protostuff序列化数据");
    }
}
