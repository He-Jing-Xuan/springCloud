package com.he.springcloud.controller;

import com.he.springcloud.entities.CommonResult;
import com.he.springcloud.entities.Payment;
import com.he.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController   //必须是这个注解，因为是模拟前后端分离的restful风格的请求，要求每个方法返回 json
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @PostMapping(value = "/payment/create")
    //	    注意这里的 @RequestBody  是必须要写的，虽然 MVC可以自动封装参数成为对象，
    //      但是当消费者项目调用，它传参是 payment 整个实例对象传过来的， 即Json数据，因此需要写这个注解
    // https://blog.csdn.net/weixin_38004638/article/details/99655322
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);

        if(result > 0){
            return new CommonResult(200, "插入数据库成功, 服务端口号："+serverPort, result);
        }
        return new CommonResult(444, "插入数据库失败,  服务端口号："+serverPort, null);
    }

    @GetMapping(value = "/payment/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        Payment result = paymentService.getPaymentById(id);
       // log.info("****查询结果：" + result);
        if(result != null){
            return new CommonResult(200, "查询成功!  服务端口号："+serverPort, result);
        }
        return new CommonResult(444, "没有对应id的记录, 服务端口号："+serverPort, null);
    }

    /**
     * 超时设置，故意设置超时演示出错情况：
     *
     * 服务提供方8001故意写暂停程序
     * 服务消费方80添加超时方法PaymentFeignService
     * 服务消费方80添加超时方法OrderFeignControIIer
     * 测试：http://localhost/consumer/payment/feign/timeout
     * 错误页面
     * @return
     */
    //8001提供方
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }
}
