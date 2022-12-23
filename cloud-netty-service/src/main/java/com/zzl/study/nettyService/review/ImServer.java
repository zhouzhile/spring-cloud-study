package com.zzl.study.nettyService.review;

import com.zzl.study.nettyService.review.handle.client.MyMsgToByteEncoder;
import com.zzl.study.nettyService.review.handle.server.ImServerHandler;
import com.zzl.study.nettyService.review.handle.server.MyByteToMsgDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @ClassName ImServer
 * @Desc Im服务端，实现客户端与客户端即时通讯(通过服务端转发)，并带有心跳监测功能
 * @Author Lenovo
 * @Date 2022/12/23 15:35
 * @Version 1.0
 **/
public class ImServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup wokerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(bossGroup, wokerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MyMsgToByteEncoder());
                            ch.pipeline().addLast(new MyByteToMsgDecoder());
                            ch.pipeline().addLast(new IdleStateHandler(10,0,0));
                            ch.pipeline().addLast(new ImServerHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(9000).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }
    }
}
