package com.zzl.study.nettyService.review.handle.client;

import com.zzl.study.nettyService.review.protocal.MyProtocal;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @ClassName ClientMsgHander
 * @Desc 发送消息时将消息包装成自定义协议进行发送
 * @Author Lenovo
 * @Date 2022/12/22 15:42
 * @Version 1.0
 **/
public class MyProtocalClientHandler extends SimpleChannelInboundHandler<MyProtocal> {

    /**
     * 当通道就绪，就会触发这个方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i =0 ;i<500;i++){
            MyProtocal message = new MyProtocal();
            byte[] bytes = "周智乐".getBytes(CharsetUtil.UTF_8);
            message.setLen(bytes.length);
            message.setBytes(bytes);
            ctx.writeAndFlush(message);
        }
        System.out.println("客户端发送数据完成");
    }

    /**
     * 读取客户端返回的数据
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyProtocal msg) throws Exception {

    }
}
