package com.zzl.study.nettyService.review.handle.server;

import com.zzl.study.nettyService.review.protocal.MyProtocal;
import com.zzl.study.nettyService.split.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName ImServerHandler
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/12/23 15:53
 * @Version 1.0
 **/
public class ImServerHandler extends SimpleChannelInboundHandler<MyProtocal> {

    int readIdleTimes = 0;
    // 保存所有的客户端对应的channel，当一个channel有消息时，将这个消息转发给其他的客户端channel
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 客户端和服务端创建连接，就会触发这个方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 将channel保存到channelGroup
        Channel channel = ctx.channel();
        channelGroup.add(channel);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyProtocal myProtocal) throws Exception {
        Channel channel = ctx.channel();
        String message = new String(myProtocal.getBytes(), CharsetUtil.UTF_8);
        if ("Heartbeat pachage".equals(message)){
            MyProtocal msg = new MyProtocal("ok");
            ChannelFuture channelFuture = channel.writeAndFlush(msg);
            if (channelFuture.isSuccess()){
                readIdleTimes = 0;
            }
        }else {
            // 遍历channelGroup，判断是不是当前channel，如果是发送：本机.... 如果不是，发送：客户端***....
            channelGroup.forEach(c->{
                MyProtocal msg;
                if (c == channel){
                    msg = new MyProtocal("【本机】说:"+message);
                }else {
                    msg = new MyProtocal("客户端【" + channel.remoteAddress() + "】说:" + message);
                }
                ChannelFuture channelFuture = c.writeAndFlush(msg);
                if (channelFuture.isSuccess()){
                    readIdleTimes = 0;
                }
            });
        }
    }

    /**
     * 我们在服务端添加了IdleStateHandler,并配置了读超时10s,所以在10s内没有发生读事件时，就会触发下面的方法
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
        String eventType = null;
        switch (idleStateEvent.state()){
            case READER_IDLE:
                eventType = "读空闲";
                readIdleTimes++;
                break;
            case WRITER_IDLE:
                eventType = "写空闲";
                break;
            case ALL_IDLE:
                eventType = "读写空闲";
                break;
        }
        if (readIdleTimes > 3) {
            System.out.println(" [server]读空闲超过3次，关闭连接，释放更多资源");
            MyProtocal message = new MyProtocal("idle close");
            ctx.channel().writeAndFlush(message);
            ctx.channel().close();
        }

    }
}
