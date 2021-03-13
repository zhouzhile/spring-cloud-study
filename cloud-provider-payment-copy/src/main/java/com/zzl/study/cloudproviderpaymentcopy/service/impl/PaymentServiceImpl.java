package com.zzl.study.cloudproviderpaymentcopy.service.impl;

import com.zzl.study.cloudcommon.entities.Payment;
import com.zzl.study.cloudproviderpaymentcopy.dao.PaymentDao;
import com.zzl.study.cloudproviderpaymentcopy.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;

    @Value("${server.port}")
    private String serverPort;

    @Override
    public Integer createPayment(Payment payment) {
        System.out.println("端口号:"+serverPort);
        Integer count = paymentDao.createPayment(payment);
        return count;
    }

    @Override
    public Payment getPaymentById(Long id) {
        System.out.println("端口号:"+serverPort);
        return paymentDao.getPaymentById(id);
    }
}
