package com.zzl.study.cloudshardingservice;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName NettyTest
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/5/31 16:26
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class NettyBIOTest {

    public static void main(String[] args) throws IOException {
        // 创建ServerSocket，负责接收用户的连接请求
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            System.out.println("等待连接。。");
            /**
             * 从连接请求队列中取出一个客户的连接请求，然后创建与客户连接的Socket对象，并返回这个Socket对象
             */
            Socket socket = serverSocket.accept();
            System.out.println("有客户端连接了。。");
            new Thread(()->{
                try {
                    handler(socket);
                } catch (IOException e) {
                    System.out.println("错误信息"+e.getMessage());
                }
            }).start();
        }
    }

    private static void handler(Socket socket) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println("准备read。。");
        //接收客户端的数据，阻塞方法，没有数据可读时就阻塞
        int read = socket.getInputStream().read(bytes);
        System.out.println("read完毕。。");
        if (read !=-1) {
            System.out.println("接收到客户端的数据：" + new String(bytes, 0, read));
        }
        socket.getOutputStream().write("HelloClient".getBytes());
        socket.getOutputStream().flush();
    }
}
