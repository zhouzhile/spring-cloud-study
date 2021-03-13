package com.zzl.study.cloudconsumerorder.controller;

import com.zzl.study.cloudcommon.entities.Payment;
import com.zzl.study.cloudcommon.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/payment")
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    public static final String url = "http://cloud-provider-payment";

    @RequestMapping(value = "creat")
    public CommonResult<Payment> creatPayment( Payment payment){

        return restTemplate.postForObject(url+"/payment/creat",payment,CommonResult.class);
    }

    @RequestMapping(value = "getPaymentById")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){

        return restTemplate.getForObject(url+"/payment/getPaymentById/"+id,CommonResult.class);
    }


}
