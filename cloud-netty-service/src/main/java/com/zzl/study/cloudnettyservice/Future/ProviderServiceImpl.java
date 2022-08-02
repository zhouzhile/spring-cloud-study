package com.zzl.study.cloudnettyservice.Future;

import com.zzl.study.cloudnettyservice.code.pojo.User;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName ProviderServiceImmpl
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/7/9 9:01
 * @Version 1.0
 **/
public class ProviderServiceImpl implements ProviderService{
    @Override
    public String sayHello(String name) {
        return "hello"+name;
    }

    @Override
    public CompletableFuture<User> getUserInfo() {
        return CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("回调"+System.currentTimeMillis());
            User user = new User();
            user.setId(111);
            user.setName("aaa");
            return user;
        });
    }
}
