package com.zzl.study.nettyService.review.handle.server;

import com.zzl.study.nettyService.review.protocal.MyProtocal;
import com.zzl.study.nettyService.split.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName MyByteToMsgDecoder
 * @Desc 解码
 * @Author Lenovo
 * @Date 2022/12/22 23:21
 * @Version 1.0
 **/
public class MyByteToMsgDecoder extends ByteToMessageDecoder {

    int length = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 因为Message中len为整形，占4个字节，所以只要这里大于4，就表示有数据
        while (in.readableBytes() > 4){
            if (length == 0){
                // 先去读4个字节，获取这4个字节的内容，也就是消息体的长度
                length = in.readInt();
            }
            // in.readableBytes()表示当前可读的消息的长度，如果小于length,表示这个消息体还没有传输完成，所以等待
            if (in.readableBytes() < length){
                System.out.println("当前可读数据不够，继续等待");
                return;
            }
            byte[] content = new byte[length];
            if (in.readableBytes() >= length){
                in.readBytes(content);
                // 封装成Message对象，传递给下一个Handler
                MyProtocal message = new MyProtocal();
                message.setLen(length);
                message.setBytes(content);
                out.add(message);
            }
            length = 0;
        }
    }
}
