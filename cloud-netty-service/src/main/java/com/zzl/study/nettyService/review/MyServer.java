package com.zzl.study.nettyService.review;

import com.zzl.study.nettyService.review.handle.server.MyByteToMsgDecoder;
import com.zzl.study.nettyService.review.handle.server.MyProtocalServerHandler;
import com.zzl.study.nettyService.review.handle.server.ServerSimpleHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName MyServer
 * @Desc Netty服务端
 * @Author Lenovo
 * @Date 2022/12/22 14:23
 * @Version 1.0
 **/
public class MyServer {
    public static void main(String[] args) {

        /**
         * 创建事件循环组：BoosGroup和WorkerGroup
         * BoosGroup中的NioEventLoop只负责创建连接，WorkerGroup中的NioEventLoop负责处理读写请求
         * BoosGroup和WorkerGroup中默认的子线程数量为：CPU核数*2
         */
        NioEventLoopGroup boosGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup wokerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(boosGroup,wokerGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class) // 设置通道的类型
                    .option(ChannelOption.SO_BACKLOG,128) // 设置线程队列等待连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE,Boolean.TRUE) // 设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 给WorkerGroup中的NioEventLoop对应的管道设置处理器，也就是给PipeLine中设置ChannelHandler
//                            ch.pipeline().addLast(new ServerSimpleHandler());
                            ch.pipeline().addLast(new MyByteToMsgDecoder());
                            ch.pipeline().addLast(new MyProtocalServerHandler());
                        }
                    });
            // 绑定端口，并启动
            ChannelFuture channelFuture = serverBootstrap.bind(9000).sync();
            // 对关闭通道进行 监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }

    }

}
