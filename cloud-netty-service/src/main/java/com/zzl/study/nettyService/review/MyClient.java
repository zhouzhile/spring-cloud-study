package com.zzl.study.nettyService.review;

import com.zzl.study.nettyService.review.handle.client.ClientSimpleHander;
import com.zzl.study.nettyService.review.handle.client.MyMsgToByteEncoder;
import com.zzl.study.nettyService.review.handle.client.MyProtocalClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName MyClient
 * @Desc Netty客户端
 * @Author Lenovo
 * @Date 2022/12/22 14:22
 * @Version 1.0
 **/
public class MyClient {

    public static void main(String[] args) {
        try {
            // 客户端只有一个线程组
            NioEventLoopGroup group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class) // 设置通道类型
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new ClientSimpleHander()); // 最简单的处理器
                            ch.pipeline().addLast(new MyMsgToByteEncoder());
                            ch.pipeline().addLast(new MyProtocalClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            // 给关闭通道设置监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
