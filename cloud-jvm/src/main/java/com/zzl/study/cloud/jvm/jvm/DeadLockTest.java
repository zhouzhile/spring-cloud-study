//package com.zzl.study.cloud.jvm.jvm;
//
//public class DeadLockTest {
//
//    public static Object lock1 = new Object();
//    public static Object lock2 = new Object();
//
//    public static void main(String[] args) {
//
//        new Thread(()->{
//            synchronized (lock1){
//                try {
//                    System.out.println("Thread1 开始运行");
//                    Thread.sleep(5000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (lock2){
//                    System.out.println("Thread1 结束运行");
//                }
//            }
//        }).start();
//
//        new Thread(()->{
//            synchronized (lock2){
//                try {
//                    System.out.println("Thread2 开始运行");
//                    Thread.sleep(5000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (lock1){
//                    System.out.println("Thread2 结束运行");
//                }
//            }
//        }).start();
//    }
//}
