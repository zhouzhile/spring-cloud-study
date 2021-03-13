package com.zzl.study.cloudconsumerorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CloudConsumerOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerOrderApplication.class, args);
    }

}
