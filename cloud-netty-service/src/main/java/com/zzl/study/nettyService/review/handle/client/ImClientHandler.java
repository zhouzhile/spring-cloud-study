package com.zzl.study.nettyService.review.handle.client;

import com.zzl.study.nettyService.review.protocal.MyProtocal;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @ClassName ImClientHandler
 * @Desc 只接收服务端的消息
 * @Author Lenovo
 * @Date 2022/12/23 15:23
 * @Version 1.0
 **/
public class ImClientHandler extends SimpleChannelInboundHandler<MyProtocal> {

    /**
     * 客户端和服务端创建连接，就会触发这个方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 通过客户端命令行实现了消息的发送，所以这里没有实现
    }

    /**
     * 读取服务端返回的数据
     *
     * @param ctx
     * @param myProtocal
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyProtocal myProtocal) throws Exception {
        // 获取到客户端发送的消息内容
        String message = new String(myProtocal.getBytes(), CharsetUtil.UTF_8);
        // 客户端打印心跳包
        System.out.println(" client received :" + message);
        if (message != null && message.equals("idle close")) {
            System.out.println(" 服务端关闭连接，客户端也关闭");
            ctx.channel().closeFuture();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端-客户端失去连接，重连中...");
    }
}
