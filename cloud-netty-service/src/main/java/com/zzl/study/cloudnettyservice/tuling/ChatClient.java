package com.zzl.study.cloudnettyservice.tuling;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @ClassName ChatClient
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/10 11:37
 * @Version 1.0
 **/
public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建客户端
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder",new StringDecoder());
                            pipeline.addLast("encoder",new StringEncoder());
                            pipeline.addLast(new ChatClientHandler());
                        }
                    });
            // 启动客户端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            new Thread(()->{
                Channel channel = channelFuture.channel();
                while (channel.isActive()){
                    try {
                        Thread.currentThread().sleep(2000);
                        channel.writeAndFlush("Heartbeat pachage");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            // 得到Channel
            Channel channel = channelFuture.channel();
            // 将控制台写入的数据，放入到channel中
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg);
            }
        }finally {
            group.shutdownGracefully();
        }
    }
}
