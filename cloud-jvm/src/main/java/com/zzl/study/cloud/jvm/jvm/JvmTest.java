package com.zzl.study.cloud.jvm.jvm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JvmTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //测试自定义类加载器
//        MyClassLoader myClassLoader = new MyClassLoader("D:/tmp");
//        Class<?> aClass = myClassLoader.loadClass("com.zzl.study.cloud.jvm.jvm.User");
//        Object o = aClass.newInstance();
//        Method method = aClass.getDeclaredMethod("usersay", null);
//        Object invoke = method.invoke(o, null);
//        System.out.println("类加载器"+aClass.getClassLoader().getClass().getName());

        //测试打破双亲委派机制
        MyClassLoader2 myClassLoader2 = new MyClassLoader2("D:/tmp");
        Class<?> aClass = myClassLoader2.loadClass("com.zzl.study.cloud.jvm.jvm.User");
        Object o = aClass.newInstance();
        Method method = aClass.getDeclaredMethod("usersay", null);
        Object invoke = method.invoke(o, null);

        MyClassLoader2 myClassLoader3 = new MyClassLoader2("D:/tmp1");
        Class<?> aClass3 = myClassLoader3.loadClass("com.zzl.study.cloud.jvm.jvm.User");
        Object o3 = aClass3.newInstance();
        Method method3 = aClass3.getDeclaredMethod("usersay", null);
        Object invoke3 = method3.invoke(o3, null);
    }
}
