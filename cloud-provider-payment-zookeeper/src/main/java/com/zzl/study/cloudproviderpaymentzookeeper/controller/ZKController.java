package com.zzl.study.cloudproviderpaymentzookeeper.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/zookeeper")
public class ZKController {

    @Value(value = "${server.port}")
    private String serverPort;

    @RequestMapping(value = "/getPort")
    public String getPort(){

        return "Zookeeper服务端口"+serverPort;
    }

}
