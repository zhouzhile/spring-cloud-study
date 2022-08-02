package com.zzl.study.cloudnettyservice.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName NettyClient
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/8 21:53
 * @Version 1.0
 **/
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个客户端事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建客户端启动对象,并设置相关参数
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)// 设置通道的类型
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            // 客户端连接服务端并启动
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            // 给关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }
}
