package com.zzl.study.cloudproviderpaymentcopy.dao;

import com.zzl.study.cloudcommon.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface  PaymentDao {

     Integer createPayment(Payment payment);

     Payment getPaymentById(Long id);

}
