package com.he.springcloud.dao;

import com.he.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

// 是ibatis下面的注解 //@Repositoty有时候会有问题
@Mapper
@Repository
public interface PaymentDao {
    int create(Payment payment);
    Payment getPaymentById(@Param("id") Long id);
}

