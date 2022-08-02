package com.zzl.study.cloudnettyservice.IM;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName ServiceClient
 * @Desc 服务端代码
 * @Author Lenovo
 * @Date 2022/6/5 11:07
 * @Version 1.0
 **/
public class IMServer {
    // 端口号
    private static final int PORT= 9000;
    // 服务端Channel
    private ServerSocketChannel serverSocketChannel;
    // 多路复用器
    private Selector selector;

    // 初始化
    public IMServer(){
        try {
            serverSocketChannel = ServerSocketChannel.open();
            // 绑定ip和端口
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            // 创建多路复用器
            selector = Selector.open();
            // 服务端Channel注册到Selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务端准备就绪");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 监听消息
    public void listen(){
        while (true){
            try {
                // 监听所有注册的Channel
                selector.select();
                // 拿到所有有事件发生的通道对应的SelectionKey
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                // 遍历集合
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()){
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        // 设置非阻塞
                        socketChannel.configureBlocking(false);
                        // 注册到Selector
                        socketChannel.register(selector,SelectionKey.OP_READ);
                        System.out.println(socketChannel.getRemoteAddress()+" 上线 ");
                    }else if (selectionKey.isReadable()){
                        readData(selectionKey);
                    }
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读事件处理逻辑
     */
    public void readData(SelectionKey selectionKey){
        SocketChannel channel = null;
        try {
            channel = (SocketChannel)selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 读数据
            int read = channel.read(byteBuffer);
            if (read > 0){
                // 将数据转换成字符串
                String msg = new String(byteBuffer.array());
                System.out.println(channel.getRemoteAddress() + "发送消息:"+msg);
                // 转发到其他客户端
                sendInfoToOtherClient(msg,channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() +"离线了");
                // 取消注册
                selectionKey.cancel();
                // 关闭通道
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 转发某个客户端的消息到其他客户端
     */
    public void sendInfoToOtherClient(String msg,SocketChannel socketChannel) throws IOException {
        System.out.println("服务器转发消息中.....");
        // 遍历注册到Selector上的全部Channel,排除自己，转发给其他的Channel
        System.out.println("===="+selector.keys().size());
        for (SelectionKey selectionKey : selector.keys()){
            SelectableChannel targetChannel = selectionKey.channel();
            // 排除自己
            if (targetChannel instanceof SocketChannel && targetChannel!=socketChannel){
                SocketChannel channel = (SocketChannel)targetChannel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                channel.write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) {
        IMServer imServer = new IMServer();
        imServer.listen();
    }

}
