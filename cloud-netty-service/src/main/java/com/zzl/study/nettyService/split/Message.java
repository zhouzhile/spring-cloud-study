package com.zzl.study.nettyService.split;

import java.util.Arrays;

/**
 * @ClassName Message
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/15 21:09
 * @Version 1.0
 **/
public class Message {

    int len;

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
