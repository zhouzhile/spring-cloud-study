package com.zzl.study.cloudnettyservice.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName NettyServer
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/8 7:30
 * @Version 1.0
 **/
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 创建事件循环组：BoosGroup和WorkerGroup
         * BoosGroup中的NioEventLoop只负责创建连接，WorkerGroup中的NioEventLoop负责处理读写请求
         * BoosGroup和WorkerGroup中默认的子线程数量为：CPU核数*2
         */
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建一个服务器端启动对象，并配置参数
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(boosGroup,workerGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class) // 设置通道的类型
                    .option(ChannelOption.SO_BACKLOG,128) // 设置线程队列等待连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE,Boolean.TRUE) // 设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 给WorkerGroup中的NioEventLoop对应的管道设置处理器，也就是给PipeLine中设置ChannelHandler
                        // 给PipeLine设置ChannelHanler
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            // 绑定端口，并启动
            ChannelFuture channelFuture = bootstrap.bind(9000).sync();
            // 对关闭通道进行 监听
            channelFuture.channel().closeFuture().sync();
        }finally {
            // 优雅关闭
            boosGroup.shutdownGracefully();
            // 优雅关闭
            workerGroup.shutdownGracefully();
        }
    }

}
