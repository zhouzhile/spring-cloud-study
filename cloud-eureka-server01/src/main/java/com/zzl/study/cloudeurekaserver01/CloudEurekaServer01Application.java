package com.zzl.study.cloudeurekaserver01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CloudEurekaServer01Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudEurekaServer01Application.class, args);
    }

}
