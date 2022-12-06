package com.zzl.study.cloud.jvm.review;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName MySelfClassLoader2
 * @Desc 打破双亲委派机制
 * @Author Lenovo
 * @Date 2022/10/21 11:03
 * @Version 1.0
 **/
public class MySelfClassLoader2 extends ClassLoader{

    private String classPath;

    public MySelfClassLoader2(String classPath){
        this.classPath =classPath;
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (name.startsWith("com.zzl.study")) {
                        c = findClass(name);
                    } else {
                        c =  super.loadClass(name,resolve);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }


    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = classToByteArray(name);
        Class<?> aClass = defineClass(name, bytes, 0, bytes.length);
        return aClass;
    }

    private byte[] classToByteArray(String name) throws IOException {
        name.replaceAll("\\.","/");
        FileInputStream fileInputStream = new FileInputStream(classPath+"/"+name+".class");
        int available = fileInputStream.available();
        byte[] bytes = new byte[available];
        fileInputStream.read(bytes);
        fileInputStream.close();
        return bytes;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, InvocationTargetException {
        //测试打破双亲委派机制
        MySelfClassLoader2 myClassLoader2 = new MySelfClassLoader2("D:/tmp");
        Class<?> aClass = myClassLoader2.loadClass("com.zzl.study.cloud.jvm.jvm.User");
        Object o = aClass.newInstance();
        Method method = aClass.getDeclaredMethod("usersay", null);
        Object invoke = method.invoke(o, null);
        System.out.println(aClass.getClassLoader().getClass().getName());

        MySelfClassLoader2 myClassLoader3 = new MySelfClassLoader2("D:/tmp");
        Class<?> aClass3 = myClassLoader3.loadClass("com.zzl.study.cloud.jvm.jvm.User");
        Object o3 = aClass3.newInstance();
        Method method3 = aClass3.getDeclaredMethod("usersay", null);
        Object invoke3 = method3.invoke(o3, null);
    }
}

