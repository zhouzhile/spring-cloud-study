package com.zzl.study.nettyService.split;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName MyMessageDecoder
 * @Desc 解码器
 * @Author Lenovo
 * @Date 2022/6/15 21:17
 * @Version 1.0
 **/
public class MyMessageDecoder extends ByteToMessageDecoder {

    int length = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
       System.out.println("解码");
        // 因为Message中len为整形，占4个字节，所以只要这里大于4，就表示有数据
        if (in.readableBytes() >= 4){
            if (length == 0){
                length = in.readInt();
            }
            if (in.readableBytes() < length){
                System.out.println("当前可读数据不够，继续等待");
                return;
            }
            byte[] content = new byte[length];
            if (in.readableBytes() >= length){
                in.readBytes(content);
                // 封装成Message对象，传递给下一个Handler
                Message message = new Message();
                message.setLen(length);
                message.setBytes(content);
                out.add(message);
            }
            length = 0;
        }
    }
}
