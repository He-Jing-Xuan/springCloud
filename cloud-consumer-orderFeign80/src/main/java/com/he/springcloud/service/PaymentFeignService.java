package com.he.springcloud.service;

import com.he.springcloud.entities.CommonResult;
import com.he.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
@FeignClient(value = "CLOUD-PROVIDER-SERVICE")  //服务名称，要和eureka上面的一致才行
public interface PaymentFeignService {
    //这个就是provider 的controller层的方法定义。
    @GetMapping(value = "/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    //8001提供方
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}

