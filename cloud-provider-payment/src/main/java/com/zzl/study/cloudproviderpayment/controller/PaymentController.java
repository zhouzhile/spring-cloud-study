package com.zzl.study.cloudproviderpayment.controller;

import com.zzl.study.cloudcommon.result.CommonResult;
import com.zzl.study.cloudcommon.entities.Payment;
import com.zzl.study.cloudproviderpayment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Resource
    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/creat",method = RequestMethod.POST)
    public CommonResult<Integer> creatPayment(@RequestBody Payment payment){
        Integer count = paymentService.createPayment(payment);
        CommonResult commonResult = new CommonResult();
        if (count > 0){
            commonResult.setCode(200);
            commonResult.setMessage("插入数据成功");
            commonResult.setData(count);
        }else {
            commonResult.setCode(444);
            commonResult.setMessage("插入数据失败");
        }
        return commonResult;
    }

    @RequestMapping(value = "/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){

        CommonResult<Payment> commonResult = new CommonResult();
        Payment payment = paymentService.getPaymentById(id);

        if (payment == null){
            commonResult.setCode(444);
            commonResult.setMessage("查询数据不存在id:"+id);
        }else {
            commonResult.setCode(200);
            commonResult.setMessage("数据查询成功");
            commonResult.setData(payment);
        }

        return commonResult;
    }

    @RequestMapping(value = "/getEurekaServices")
    public CommonResult<List<String>> getEurekaServiceList(){
        CommonResult<List<String>> commonResult = new CommonResult<>();
        List<String> services = discoveryClient.getServices();
        if (CollectionUtils.isEmpty(services)){
            commonResult.setCode(444);
            commonResult.setData(null);
            commonResult.setMessage("尚未注册服务或者未查询到服务");
        }else {
            commonResult.setCode(200);
            commonResult.setData(services);
            commonResult.setMessage("查询成功");
        }

        return commonResult;
    }

    @RequestMapping(value = "/getInstanceInfo/{serviceId}")
    public CommonResult<List<ServiceInstance>> getInstanceInfo(@PathVariable String serviceId){
        CommonResult<List<ServiceInstance>> commonResult = new CommonResult<>();
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);

        if (CollectionUtils.isEmpty(instances)){
            commonResult.setCode(444);
            commonResult.setData(null);
            commonResult.setMessage("尚未注册服务或者未查询到服务");
        }else {
            commonResult.setCode(200);
            commonResult.setData(instances);
            commonResult.setMessage("查询成功");
        }

        return commonResult;
    }

}
