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
//                    System.out.println("Thread1 ��ʼ����");
//                    Thread.sleep(5000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (lock2){
//                    System.out.println("Thread1 ��������");
//                }
//            }
//        }).start();
//
//        new Thread(()->{
//            synchronized (lock2){
//                try {
//                    System.out.println("Thread2 ��ʼ����");
//                    Thread.sleep(5000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (lock1){
//                    System.out.println("Thread2 ��������");
//                }
//            }
//        }).start();
//    }
//}
