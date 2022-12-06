//package com.zzl.study.cloud.jvm.jvm;
//
//public class ConcurrentTest_003 {
//    public static void main(String[] args) {
//        for (int i = 0;i<10;i++){
//            new Thread(()->{
//                try {
//                    Thread.currentThread().wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//            try {
//                Thread.sleep(2000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
