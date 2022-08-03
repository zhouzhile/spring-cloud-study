package com.zzl.study.cloudshardingservice.channel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName FileChannelTest01
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/4 13:38
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileChannelTest01 {

    /**
     * 向文件中写数据
     */
    @Test
    public void writeFileTest() throws IOException {
        String msg = "Netty学习之路";
        // 创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\file.txt");
        // 从输出流中获取到Channel
        FileChannel channel = fileOutputStream.getChannel();
        // 创建Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        // 将数据写到Buffer中
        byteBuffer.put(msg.getBytes(StandardCharsets.UTF_8));
        // 将Buffer的position和limit倒置
        byteBuffer.flip();
        // 将Buffer中的数据写入到Channel中
        int read = channel.write(byteBuffer);
        fileOutputStream.close();
    }

    /**
     * 从文件读取数据
     */
    @Test
    public void readFileTest() throws IOException {
        File file = new File("D:\\file.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        // 创建Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());
        // 将通道中的数据读入到Buffer中
        channel.read(byteBuffer);
        // 将ByteBuffer中的数据转换成String
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }

    /**
     * 读写文件
     */
    @Test
    public void readWriteFileTest() throws IOException {
        // 输入
        FileInputStream fileInputStream = new FileInputStream("D:\\file.txt");
        FileChannel channel01 = fileInputStream.getChannel();
        // 输出
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\file02.txt");
        FileChannel channel02 = fileOutputStream.getChannel();
        // 创建一个Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        // 将已存在的文件读入到Buffer中
        while (true){
            byteBuffer.clear();
            int read = channel01.read(byteBuffer);
            if (read == -1){
                break;
            }
            byteBuffer.flip();
            channel02.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    /**
     * 拷贝文件
     */
    @Test
    public void copyFileTest() throws IOException {
        // 输入
        FileInputStream fileInputStream = new FileInputStream("D:\\file.txt");
        FileChannel channel01 = fileInputStream.getChannel();
        // 输出
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\file02.txt");
        FileChannel channel02 = fileOutputStream.getChannel();
        // 文件拷贝
        channel02.transferFrom(channel01,0,channel01.size());
        channel01.close();
        channel02.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
