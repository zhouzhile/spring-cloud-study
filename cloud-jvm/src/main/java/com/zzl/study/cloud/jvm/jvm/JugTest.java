//package com.zzl.study.cloud.jvm.jvm;
//
//public class JugTest {
//
//    public static void main(String[] args) {
//        for (int i = 0;i<10;i++){
//            new Thread(()->{
//                add();
//            }).start();
//        }
//
////        User user1 = new User();
////        user1.setName("q");
////        System.out.println(user1.hashCode());
////        // System.out.println(System.identityHashCode(user1));
////        User user2 = new User();
////        user2.setName("q");
////        System.out.println(user2.hashCode());
////        // System.out.println(System.identityHashCode(user2));
////        System.out.println(user1 == user2);
//    }
//
//    private static void add(){
//        User user = new User();
//        user.setName("aaaa");
//        user.setAge(1);
//        System.out.println(Thread.currentThread().getName()+" "+System.identityHashCode(user));
//    }
//
//}
