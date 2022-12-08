package com.zzl.study.magic.service;

/**
 * @ClassName PersonService
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/11/10 17:43
 * @Version 1.0
 **/
public interface PersonService {

    String say();

    default void doSomeThing(){
        System.out.println("AAAAAAAAAAAA");
    }
}
