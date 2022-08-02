package com.zzl.study.cloudnettyservice.IM;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @ClassName IMClient
 * @Desc 客户端
 * @Author Lenovo
 * @Date 2022/6/5 11:08
 * @Version 1.0
 **/
public class IMClient {

    private final String IP = "127.0.0.1";
    private final int PORT = 9000;
    private SocketChannel socketChannel;
    private Selector selector;
    private String clientName;

    public IMClient(){
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress(IP,PORT));
            // 设置为非阻塞
            socketChannel.configureBlocking(false);
            // 注册
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);
            // 得到clientName
            clientName = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println("客户端"+clientName+"准备就绪");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向服务器发送数据
     */
    public void sendInfo(String info){
        try {
            String allMsg = clientName+"发送消息: ["+info+"]";
            // 将消息放入ByteBuffer
            ByteBuffer byteBuffer = ByteBuffer.wrap(allMsg.getBytes());
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取客户端数据
     */
    public void readInfo(){
        try {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    // 读数据
                    channel.read(byteBuffer);
                    // 转换成String
                    String msg = new String(byteBuffer.array());
                    System.out.println(msg.trim());
                }
                iterator.remove();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IMClient imClient = new IMClient();
        new Thread(()->{ // 创建一个线程，一直去读取消息
            while (true){
                try {
                    imClient.readInfo();
                    Thread.currentThread().sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 将控制台输入的信息发送到服务端
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()){
            String msg = scanner.nextLine();
            imClient.sendInfo(msg);
        }
    }

}
