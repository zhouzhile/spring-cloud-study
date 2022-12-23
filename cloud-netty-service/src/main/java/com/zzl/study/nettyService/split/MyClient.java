package com.zzl.study.nettyService.split;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @ClassName MyClient
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/15 20:37
 * @Version 1.0
 **/
public class MyClient {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new StringDecoder());
//                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new MyMessageDecoder());
                            pipeline.addLast(new MyMessageEncoder());
                            pipeline.addLast(new MyClientIdleHadler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            Channel channel = channelFuture.channel();
            new Thread(()->{
                while (channel.isActive()){
                    try {
                        Thread.sleep(50*1000);
                        System.out.println("客户端发送心跳包");
                        Message message = new Message();
                        String msg = "Heartbeat pachage";
                        message.setLen(msg.getBytes(StandardCharsets.UTF_8).length);
                        message.setBytes(msg.getBytes(StandardCharsets.UTF_8));
                        channel.writeAndFlush(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String msg = scanner.nextLine();
                Message message = new Message();
                message.setLen(msg.getBytes(StandardCharsets.UTF_8).length);
                message.setBytes(msg.getBytes(StandardCharsets.UTF_8));
                channel.writeAndFlush(message);
            }
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
