package com.zzl.study.cloudshardingservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.zzl.study.cloudshardingservice.mapper")
@SpringBootApplication
public class CloudShardingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudShardingServiceApplication.class, args);
    }

}
