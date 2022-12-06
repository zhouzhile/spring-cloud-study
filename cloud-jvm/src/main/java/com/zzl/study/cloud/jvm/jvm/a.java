package com.zzl.study.cloud.jvm.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName a
 * @Desc TODO
 * @Author Lenovo
 * @Date 2021/12/27 13:51
 * @Version 1.0
 **/
public class a {

    public static void main(String [] args) {

        List<Thread> threadList = new ArrayList<>();
       Thread thread1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1完成");
            }
        });
       threadList.add(thread1);
        Thread thread2 =  new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2完成");
            }
        });
        threadList.add(thread2);
        Thread thread3 =  new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程3完成");
            }
        });
        threadList.add(thread3);
        Thread thread4 =  new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程4完成");
            }
        });
        threadList.add(thread4);
        CountDownLatch latch = new CountDownLatch(threadList.size()-1);
        for (Thread thread : threadList){
            thread.start();
        }



        try {
            /**
             * 在此阻塞，一直等到所有线程都结束为止。
             */
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end latch");
    }
}
