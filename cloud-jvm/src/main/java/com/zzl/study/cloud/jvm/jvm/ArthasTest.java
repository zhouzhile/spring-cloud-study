//package com.zzl.study.cloud.jvm.jvm;
//
//import java.util.HashSet;
//
//public class ArthasTest {
//
//    private static HashSet<String> hashSet = new HashSet();
//
//    public static void main(String[] args) {
//        // 模拟CPU过高
////        cpuHigh();
//        // 模拟线程死锁
//        deadThread();
//        // 不断的向hashSet集合增加数据
////        addset();
//    }
//
//    /**
//     * 模拟CPU过高
//     */
//    public static void cpuHigh(){
//        new Thread(()->{
//            while (true){}
//        }).start();
//    }
//
//    /**
//     * 模拟死锁
//     */
//    public static void deadThread(){
//        Object lock1 = new Object();
//        Object lock2 = new Object();
//
//        new Thread(()->{
//            synchronized (lock1){
//                System.out.println("线程1获得lock1锁");
//                try {
//                    Thread.sleep(5000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (lock2){
//                    System.out.println("线程1获得lock2锁");
//                }
//            }
//        }).start();
//
//        new Thread(()->{
//            synchronized (lock2){
//                System.out.println("线程2获得lock2锁");
//                try {
//                    Thread.sleep(5000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (lock1){
//                    System.out.println("线程2获得lock1锁");
//                }
//            }
//        }).start();
//    }
//
//    /**
//     * 无线向hashSet增加对象
//     */
//    public static void addset(){
//        new Thread(()->{
//            int count = 0;
//            while (true){
//                try {
//                    hashSet.add("count"+count);
//                    Thread.sleep(1000L);
//                    count++;
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//}
