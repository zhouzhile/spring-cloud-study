package com.zzl.study.cloud.jvm.classloader;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName MyClassLoader2
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/2/15 15:16
 * @Version 1.0
 **/
public class MyClassLoader2 extends ClassLoader{

    private String classPath;

    public MyClassLoader2(String classPath){
        this.classPath = classPath;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException{
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
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
    protected Class<?> findClass(String name){
        byte[] bytes = loadClassToByte(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassToByte(String name) throws IOException {
        name = name.replace("\\.", "/");
        FileInputStream fileInputStream =  new FileInputStream(classPath+"/"+name);
        int available = fileInputStream.available();
        byte[] bytes = new byte[available];
        fileInputStream.read(bytes);
        fileInputStream.close();
        return bytes;
    }

}
