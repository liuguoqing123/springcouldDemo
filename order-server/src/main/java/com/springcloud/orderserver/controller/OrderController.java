package com.springcloud.orderserver.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.orderserver.entity.Order;
import com.springcloud.orderserver.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/orderServer")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${spring.application.name}")
    private String papplicationName;

    @RequestMapping("/test")
    public String test(){
        return "test!";
    }

    @RequestMapping("/save")
    @HystrixCommand(fallbackMethod = "saveFallback")//熔断和降级
    public Order save(Long userId,Long productId, HttpServletRequest request){
        System.out.println("++++++++++++++执行下单的操作，orderController+++++++");
        System.out.println("papplicationName:"+papplicationName);
        String token  = request.getHeader("token");
        String cookie = request.getHeader("Cookie");
        System.out.println("token:"+token+",cookie"+cookie);

        if(userId==1L){
            int i = 1/0;  //错误的代码 (熔断时使用)
        }
        return orderService.save(userId,productId);
    }

    public Order saveFallback(Long userId, Long productId, HttpServletRequest request){
        //当下单失败后需要通知运维人员，异步通知
        new Thread(()->{
            //判断之前是否已经发送短信
            String key = "order-server";
            String val = redisTemplate.opsForValue().get(key);
            if(StringUtils.isEmpty(val)){
                //如果redis中没有key说明未发送通知，或者超过20分钟。（20分钟之内不重复给工作人员发通知。）
                System.out.println("发送短信，用户下单失败。");
                redisTemplate.opsForValue().set(key,"已通知运维人员20秒内不重复通知。",20,TimeUnit.SECONDS);
            }else{
                System.out.println(redisTemplate.opsForValue().get(key));
            }

        }).start();
        return new Order();
    }
}
