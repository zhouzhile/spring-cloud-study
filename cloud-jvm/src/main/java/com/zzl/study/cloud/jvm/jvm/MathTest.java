package com.zzl.study.cloud.jvm.jvm;

public class MathTest {

    public static final int initData = 666;
    public int compute(){
        int a =1;
        int b =2;
        int c = (a+b)*10;
        return c;
    }
    public static void main(String[] args) {
        MathTest mathTest = new MathTest();
        while (true){
            mathTest.compute();
        }
    }

}
