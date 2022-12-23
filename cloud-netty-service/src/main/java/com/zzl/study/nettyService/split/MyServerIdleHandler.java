package com.zzl.study.nettyService.split;

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
 * @ClassName MyIdleServerHandler
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/16 7:33
 * @Version 1.0
 **/
public class MyServerIdleHandler extends SimpleChannelInboundHandler<Message> {

    int readIdleTimes = 0;

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 当通道就像，就会触发该方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        Channel channel = ctx.channel();
        // 服务端拿到消息，将消息转发到客户端
        channelGroup.forEach(c->{
            String msg = new String(message.getBytes(), CharsetUtil.UTF_8);
            Message reviceMessage = new Message();
            ChannelFuture channelFuture;
            if (channel != c){
                if (msg.equals("Heartbeat pachage")){
                    String mm = "ok";
                    reviceMessage.setLen(mm.getBytes(StandardCharsets.UTF_8).length);
                    reviceMessage.setBytes(mm.getBytes(StandardCharsets.UTF_8));
                    channelFuture = c.writeAndFlush(reviceMessage);
                }else {
                    String mm = "客户端【" + channel.remoteAddress() + "】说:" + msg;
                    reviceMessage.setLen(mm.getBytes(StandardCharsets.UTF_8).length);
                    reviceMessage.setBytes(mm.getBytes(StandardCharsets.UTF_8));
                    channelFuture = c.writeAndFlush(reviceMessage);
                }
            }else {
                if (msg.equals("Heartbeat pachage")){
                    String mm = "ok";
                    reviceMessage.setLen(mm.getBytes(StandardCharsets.UTF_8).length);
                    reviceMessage.setBytes(mm.getBytes(StandardCharsets.UTF_8));
                    channelFuture = c.writeAndFlush(reviceMessage);
                }else {
                    String mm = "【本机】说:"+msg;
                    reviceMessage.setLen(mm.getBytes(StandardCharsets.UTF_8).length);
                    reviceMessage.setBytes(mm.getBytes(StandardCharsets.UTF_8));
                    channelFuture = c.writeAndFlush(reviceMessage);
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
            Message message = new Message();
            String msg = "idle close";
            message.setLen(msg.getBytes(StandardCharsets.UTF_8).length);
            message.setBytes(msg.getBytes(StandardCharsets.UTF_8));
            ctx.channel().writeAndFlush(message);
            ctx.channel().close();
        }
    }
}
