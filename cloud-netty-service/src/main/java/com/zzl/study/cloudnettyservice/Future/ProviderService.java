package com.zzl.study.cloudnettyservice.Future;

import com.zzl.study.cloudnettyservice.code.pojo.User;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName ProviderService
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/7/9 8:59
 * @Version 1.0
 **/
public interface ProviderService {


    String sayHello(String name);


    default CompletableFuture<User> getUserInfo(){
        return null;
    };


}
