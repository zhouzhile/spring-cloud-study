package com.zzl.study.cloud.jvm.classloader;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName MyClassLoader
 * @Desc 自定义类加载器
 * @Author Lenovo
 * @Date 2022/2/15 14:46
 * @Version 1.0
 **/
public class MyClassLoader extends ClassLoader{

    private String classPath;

    public MyClassLoader(String classPath){
        this.classPath = classPath;
    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) {
        // 1.根据类的全限定名，将类转换为字节数组
        byte[] bytes = loadClassToByte(name);
        // 2.调用defindClass()方法
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassToByte(String name) throws IOException {
        name = name.replace("\\.", "/");
        FileInputStream fileInputStream = new FileInputStream(classPath+"/"+name);
        int available = fileInputStream.available();
        byte[] bytes = new byte[available];
        fileInputStream.read(bytes);
        fileInputStream.close();
        return bytes;
    }
}
