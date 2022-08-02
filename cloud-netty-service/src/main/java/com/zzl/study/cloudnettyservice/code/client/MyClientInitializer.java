package com.zzl.study.cloudnettyservice.code.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @ClassName MyClientInitializer
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/12 17:01
 * @Version 1.0
 **/
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 添加自定义的编码器
        pipeline.addLast(new MyObjectToByteEncode());
        // 添加自定义的业务
        pipeline.addLast(new MyClientHandler());
    }
}
