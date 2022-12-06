package com.zzl.study.cloud.jvm.review;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName MySelfClassLoader
 * @Desc 自定义类加载器
 * @Author zhouzhile
 * @Date 2022/10/21 9:47
 * @Version 1.0
 **/
public class MySelfClassLoader extends ClassLoader{

    private String classPath;

    public MySelfClassLoader(String classPath){
        this.classPath = classPath;
    }

    /**
     * 重写ClassLoader的findClass方法
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = classToByteArray(name);
        Class<?> aClass = defineClass(name, bytes, 0, bytes.length);
        return aClass;
    }

    /**
     * 根据name将class文件加载进来，并转换成byte[]
     */
    private byte[] classToByteArray(String name) throws IOException {
        name.replaceAll("\\.","/");
        FileInputStream fileInputStream = new FileInputStream(classPath+"/"+name+".class");
        // 获取输入流中字节长度
        int available = fileInputStream.available();
        // 创建byte数组，并将输入流中的内容读取到数组内部
        byte[] bytes = new byte[available];
        fileInputStream.read(bytes);
        fileInputStream.close();
        return bytes;
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        MySelfClassLoader mySelfClassLoader = new MySelfClassLoader("D:\\tmp");
        Class<?> aClass = mySelfClassLoader.loadClass("com.zzl.study.cloud.jvm.jvm.User");
        Object object = aClass.newInstance();
        Method method = aClass.getMethod("usersay", null);
        Object invoke = method.invoke(object);
        System.out.println("类加载器"+aClass.getClassLoader().getClass().getName());
    }
}
