package com.zzl.study.cloudshardingservice;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName NettyNIOTest
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/1 7:19
 * @Version 1.0
 **/
public class NettyNIOTest {
    // 保存客户端连接
    static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {

        // 打开服务器的 套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定ip,port...准备接收客户端的连接
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(9000));
        // 设置ServerSocketChannel为非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务启动成功");

        while (true) {
            // 接收客户端的连接，在这里阻塞与否可以进行配置
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) { // 如果有客户端进行连接
                System.out.println("连接成功");
                // 设置SocketChannel为非阻塞
                socketChannel.configureBlocking(false);
                // 保存客户端连接在List中
                channelList.add(socketChannel);
            }
            // 遍历连接进行数据读取
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel socketChannel1 = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(16);
                // 非阻塞模式read方法不会阻塞，否则会阻塞
                int len = socketChannel1.read(byteBuffer);
                // 如果有数据，把数据打印出来
                if (len > 0) {
                    System.out.println("接收到消息：" + new String(byteBuffer.array()));
                } else if (len == -1) { // 如果客户端断开，把socket从集合中去掉
                    iterator.remove();
                    System.out.println("客户端断开连接");
                }
            }
        }
    }
}
