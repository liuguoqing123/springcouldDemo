package com.springcloud.orderserver.service.impl;

import com.springcloud.orderserver.entity.Order;
import com.springcloud.orderserver.service.OrderService;
import com.springcloud.productapi.entity.Product;
import com.springcloud.productapi.feign.ProductFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceimpl implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderServiceimpl.class);
    @Autowired
    private ProductFeignApi productFeignApi;

    @Override
    public Order save(Long userId, Long productId) {
        logger.info("发起下单操作。");
        Product product = productFeignApi.find(productId);
        if(product == null){
            return null;
        }
        System.out.println("++++++++++++product+++++++++++:"+product);
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderNo(UUID.randomUUID().toString().replace("-",""));
        order.setCreateTime(new Date());
        order.setProdcutName(product.getName());
        order.setProductPrice(product.getPrice());
        return order;
    }

    //ribbon调用的方式
    /*@Autowired
    private RestTemplate restTemplate;
    @Override
    public Order save(Long userId, String productId) {
        Product product = restTemplate.getForObject("http://product-server/productServer/find?id="+productId,Product.class);
        System.out.println("++++++++++++product+++++++++++:"+product);
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderNo(UUID.randomUUID().toString().replace("-",""));
        order.setCreateTime(new Date());
        order.setProdcutName(product.getName());
        order.setProductPrice(product.getPrice());
        //订单入库保存
        return order;
    }*/
}
