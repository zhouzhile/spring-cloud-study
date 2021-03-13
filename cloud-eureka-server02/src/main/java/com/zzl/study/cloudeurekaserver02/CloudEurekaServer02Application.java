package com.zzl.study.cloudeurekaserver02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CloudEurekaServer02Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudEurekaServer02Application.class, args);
    }

}
