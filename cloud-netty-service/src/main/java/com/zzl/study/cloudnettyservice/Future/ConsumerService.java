package com.zzl.study.cloudnettyservice.Future;

import com.zzl.study.cloudnettyservice.code.pojo.User;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName ConsumerService
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/7/9 9:03
 * @Version 1.0
 **/
public class ConsumerService {

    public static void main(String[] args) {

        ProviderService providerService = new ProviderServiceImpl();

        CompletableFuture<User> future = providerService.getUserInfo();
        String msg = providerService.sayHello("周智乐");
        System.out.println(msg+System.currentTimeMillis());

    }
}
