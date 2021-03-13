package com.zzl.study.cloudproviderpaymentcopy.controller;

import com.zzl.study.cloudcommon.entities.Payment;
import com.zzl.study.cloudcommon.result.CommonResult;
import com.zzl.study.cloudproviderpaymentcopy.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

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


}
