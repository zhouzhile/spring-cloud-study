//package com.zzl.study.cloud.jvm.jvm;
//
//public class ConcurrentTest_002 {
//    private static volatile int i = 0;
//
//    private static Object lock = new Object();
//
//    public static void main(String[] args) {
//
//        for (int i = 0;i<10;i++){
//            new Thread(()->{
//                for (int j = 0;j<1000;j++){
//                    synchronized (lock){
//                        add();
//                    }
//                }
//            }).start();
//        }
//        try {
//            Thread.sleep(3000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(i);
//    }
//
//    private static void add(){
//        i++;
//    }
//}
