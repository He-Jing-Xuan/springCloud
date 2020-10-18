package com.he.springcloud.controller;

import com.he.springcloud.entities.CommonResult;
import com.he.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {
    // 重点是这里，改成 提供者在Eureka 上的名称，而且无需写端口号
    public static final String PAYMENT_URL = "http://CLOUD-PROVIDER-SERVICE";//取决于我们在提供者出配置的name，CLOUD-PAYMENY-SERVICE，//同时要注意使用@LoadBalanced注解赋予RestTemplate负载均衡能力
    @Resource
    private RestTemplate restTemplate;
    @PostMapping("customer/payment/create")
    public CommonResult<Payment> create (@RequestBody Payment payment){

        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",//请求地址
                payment,//请求参数
                CommonResult.class);//返回类型
    }

    @GetMapping("customer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id,//请求地址
                CommonResult.class);//返回类型
    }
}
