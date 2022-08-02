package com.zzl.study.cloudnettyservice.tuling;

import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;


/**
 * @ClassName ChatServerHandler
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/10 10:55
 * @Version 1.0
 **/
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    // 定义ChannelGroup,服务器转发消息时，会遍历这个Group中的所有Channel，进行消息转发
    // GlobalEventExecutor.INSTANCE全局事件执行器，是单例的
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    int readOutTime = 0;
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            String eventType = null;
            switch (event.state()){
                case READER_IDLE:
                     eventType = "读空闲";
                     readOutTime++;
                     break;
                case WRITER_IDLE:
                     eventType = "写空闲";
                     break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress()+"发生了超时事件:"+eventType);
            if (readOutTime >= 3){
                // 发生3次读超时，断开连接
                ctx.channel().writeAndFlush("idle close");
                ctx.channel().close();
            }
        }
    }

    /**
     * 表示连接建立，第一个被执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 某个客户端上线，将上线消息通知到其他客户端
        channelGroup.writeAndFlush("客户端【"+channel.remoteAddress()+"】上线了");
        // 将这个Channel添加到channelGroup
        channelGroup.add(channel);

    }

    /**
     * Channel创建成功，处于活动状态，在这里我们提示客户端上线
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("客户端【"+channel.remoteAddress()+"】上线了");
    }

    /**
     * 客户端与服务端断开连接，就会触发这个方法，在这里我们提示离线
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 某个客户端离线，将离线信息推送给其他客户端
        channelGroup.writeAndFlush("客户端【"+channel.remoteAddress()+"】离线了");
        System.out.println("客户端【"+channel.remoteAddress()+"】离线了");
        System.out.println("channelGroup size=" + channelGroup.size());
    }

    /**
     * 读取客户端的数据，并实现转发
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
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
                readOutTime = 0;
            }
        });
    }

    /**
     * 关闭通道
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
