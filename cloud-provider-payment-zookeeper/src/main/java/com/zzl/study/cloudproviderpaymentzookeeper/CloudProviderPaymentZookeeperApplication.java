package com.zzl.study.cloudproviderpaymentzookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudProviderPaymentZookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudProviderPaymentZookeeperApplication.class, args);
    }

}
