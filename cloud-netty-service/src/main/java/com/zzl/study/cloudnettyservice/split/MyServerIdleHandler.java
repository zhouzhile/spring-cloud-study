package com.zzl.study.cloudnettyservice.split;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName MyIdleServerHandler
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/16 7:33
 * @Version 1.0
 **/
public class MyServerIdleHandler extends SimpleChannelInboundHandler<String> {

    int readIdleTimes = 0;

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        // 服务端拿到消息，将消息转发到客户端
        channelGroup.forEach(c->{
            ChannelFuture channelFuture;
            if (channel != c){
                if (msg.equals("Heartbeat pachage")){
                    channelFuture = c.writeAndFlush("ok");
                }else {
                    channelFuture = c.writeAndFlush("客户端【" + channel.remoteAddress() + "】说:" + msg);
                }
            }else {
                if (msg.equals("Heartbeat pachage")){
                    channelFuture = c.writeAndFlush("ok");
                }else {
                    channelFuture = c.writeAndFlush("【本机】说:"+msg);
                }

            }
            if (channelFuture.isSuccess()){
                readIdleTimes = 0;
            }
        });
    }

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
        System.out.println(ctx.channel().remoteAddress() + "超时事件：" + eventType);
        if (readIdleTimes > 3) {
            System.out.println(" [server]读空闲超过3次，关闭连接，释放更多资源");
            ctx.channel().writeAndFlush("idle close");
            ctx.channel().close();
        }
    }
}
