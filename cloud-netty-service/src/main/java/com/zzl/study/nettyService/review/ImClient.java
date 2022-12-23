package com.zzl.study.nettyService.review;

import com.zzl.study.nettyService.review.handle.client.ImClientHandler;
import com.zzl.study.nettyService.review.handle.client.MyMsgToByteEncoder;
import com.zzl.study.nettyService.review.handle.server.MyByteToMsgDecoder;
import com.zzl.study.nettyService.review.protocal.MyProtocal;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * @ClassName ImClient
 * @Desc Im客户端，实现客户端与客户端即时通讯(通过服务端转发)，并带有心跳监测功能
 * @Author Lenovo
 * @Date 2022/12/23 14:59
 * @Version 1.0
 **/
public class ImClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 因为要接收客户端发送的消息，需要先进行解码
                            ch.pipeline().addLast(new MyByteToMsgDecoder());
                            // 因为在自己创建的线程或命令行输入的内容，都是包装成MyProtocal协议发送的，所以需要编码
                            ch.pipeline().addLast(new MyMsgToByteEncoder());
                            // 本客户端还需要接收服务端转发的消息，实现通讯
                            ch.pipeline().addLast(new ImClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            Channel channel = channelFuture.channel();
            // 创建一个线程，发送心跳包
            new Thread(()->{
                while (channel.isActive()){
                    try {
                        Thread.sleep(5*1000);
                        // 为了避免发送消息时，出现粘包、拆包的现象，使用自定义的协议
                        MyProtocal message = new MyProtocal("Heartbeat pachage");
                        channel.writeAndFlush(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            // 客户端在命令行输入，实现通讯功能
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                // 接收命令行收到的数据，包装成特定的协议，发送
                String msg = scanner.nextLine();
                MyProtocal message = new MyProtocal(msg);
                channel.writeAndFlush(message);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}
