package com.zzl.study.cloudproviderpaymentcopy.service;

import com.zzl.study.cloudcommon.entities.Payment;

public interface PaymentService {

    Integer createPayment(Payment payment);

    Payment getPaymentById(Long id);

}
