package com.zzl.study.cloudconsumerzookeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/zookeeper")
public class ZookeeperConsumerController {

    public static final String url = "http://cloud-provider-payment";

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/getPort")
    public String getProviderServerPort(){
        return restTemplate.getForObject(url+"/zookeeper/getPort",String.class);
    }

}
