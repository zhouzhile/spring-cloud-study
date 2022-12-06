package com.zzl.study.cloud.jvm.jvm;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 自定义类加载器
 *    思路：实现findClass()方法
 */
public class MyClassLoader extends ClassLoader{

    private String classPath;

    public MyClassLoader(String classPath){
        this.classPath = classPath;
    }

    /**
     * findClass方法，参数为全类名
     * 思路：
     *     1.根据全类名找到对应的类，将这个类转换为字节数组
     *     2.调用
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @SneakyThrows
    @Override
    protected Class<?> findClass(String name){
        byte[] bytes = loadClassToByte(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * 根据类全路径，找到对应类，转换为字节数组
     *
     * @return
     */
    private byte[] loadClassToByte(String name) throws IOException {
        name = name.replaceAll("\\.","/");
        // 根据全路径找到类，转换为输入流
        FileInputStream fileInputStream = new FileInputStream(classPath+"/"+name+".class");
        int available = fileInputStream.available();
        byte[] bytes = new byte[available];

        fileInputStream.read(bytes);
        fileInputStream.close();
        return bytes;
    }
}
