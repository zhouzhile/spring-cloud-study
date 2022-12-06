package com.zzl.study.cloud.jvm.jvm;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 打破双亲委派机制
 *   思路：
 *       1.重写loadClass()方法
 *       2.实现findClass()方法
 */
public class MyClassLoader2 extends ClassLoader{

    private String classPath;

    public MyClassLoader2(String classPath){
        this.classPath = classPath;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                // If still not found, then invoke findClass in order
                // to find the class.
                long t1 = System.nanoTime();
                if (!name.startsWith("com.zzl.study")){
                    c = this.getParent().loadClass(name);
                }else {
                    c = findClass(name);
                }

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) {
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
