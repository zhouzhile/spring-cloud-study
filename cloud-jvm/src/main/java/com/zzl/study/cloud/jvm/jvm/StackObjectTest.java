//package com.zzl.study.cloud.jvm.jvm;
//
//
//public class StackObjectTest {
//
//    public static void main(String[] args) {
//        long startTime = System.currentTimeMillis();
//        for (int i = 0 ;i<100000000;i++){
//            creatUser();
//        }
//        System.out.println(System.currentTimeMillis()-startTime);
//    }
//
//
//    private static void creatUser(){
//        User user = new User();
//        user.setName("张三");
//        user.setAge(21);
//    }
//}
