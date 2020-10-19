package com.he.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 服务端降级@HystrixCommand
 * 首先 对 service进行配置（对容易超时的方法进行配置) :
 *
 * 降级配置：@HystrixCommand，可以在里面指定超时/出错的回调方法，作为兜底方法
 *
 * 提供方 service方法：演示超时
 */
@Service
public class PaymentService {
    /* 可以正常访问的方法*/
    public String paymentinfo_Ok(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "--paymentInfo_OK，id:" + id;
    }

    /* 超时访问的方法 */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",//超时后回调方法
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",//时间单位
                            value="3000")})//超时时间
    public String paymentinfo_Timeout(Integer id){
        int interTime = 3;
        try{
            TimeUnit.SECONDS.sleep(interTime);//模拟超时
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "--paymentInfo_Timeout，id:" + id +
                "耗时" + interTime + "秒钟--";
    }

    // 兜底方法
    public String paymentInfo_TimeOutHandler(Integer id){ // 回调函数向调用方返回一个符合预期的、可处理的备选响应
        return "线程池:  "+Thread.currentThread().getName()+"  8001系统繁忙或者运行报错，请稍后再试,id:  "+id+"\t"+"o(╥﹏╥)o";
    }
}
