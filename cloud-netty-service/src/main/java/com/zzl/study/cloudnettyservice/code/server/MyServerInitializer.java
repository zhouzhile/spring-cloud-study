package com.zzl.study.cloudnettyservice.code.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * @ClassName MyServerInitializer
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/12 16:47
 * @Version 1.0
 **/
public class MyServerInitializer extends ChannelInitializer {
    // 向Pipeline中添加ChannelHandler
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 添加自定义的解码器Handler
        pipeline.addLast(new MyByteToObjectDecoder());
        // 业务逻辑Handler
        //pipeline.addLast(new MyServerHander());
    }
}
