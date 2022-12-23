package com.zzl.study.nettyService.review.protocal;

import java.util.Arrays;

/**
 * @ClassName Message
 * @Desc 自定义通信协议
 * @Author Lenovo
 * @Date 2022/6/15 21:09
 * @Version 1.0
 **/
public class MyProtocal {

    /**
     * 信息长度
     */
    int len;

    /**
     * 信息体
     */
    byte[] bytes;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "Message{" +
                "len=" + len +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
