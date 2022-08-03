package com.zzl.study.cloudshardingservice;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClientTest {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        // 非阻塞
        socketChannel.configureBlocking(false);
        // 服务器的ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",9000);

        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("正在连接.........");
            }
        }
        // 连接成功，发送数据
        String msg = "我就试一试";
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        socketChannel.write(byteBuffer);
        System.in.read();
    }
}
