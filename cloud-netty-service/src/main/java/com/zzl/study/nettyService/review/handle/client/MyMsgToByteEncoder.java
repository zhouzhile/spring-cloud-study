package com.zzl.study.nettyService.review.handle.client;

import com.zzl.study.nettyService.review.protocal.MyProtocal;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName MyMesToByteEncoder
 * @Desc 将制定协议格式的数据 编码
 * @Author Lenovo
 * @Date 2022/12/22 23:01
 * @Version 1.0
 **/
public class MyMsgToByteEncoder extends MessageToByteEncoder<MyProtocal> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MyProtocal msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getBytes());
    }
}
