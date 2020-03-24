package com.springcloud.productserver.controller;

import com.springcloud.productapi.entity.Product;
import com.springcloud.productapi.feign.ProductFeignApi;
import com.springcloud.productserver.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductFeignClient implements ProductFeignApi {

    private final Logger logger = LoggerFactory.getLogger(ProductFeignClient.class);
    @Value("${server.port}")
    private String port;

    @Autowired
    private ProductService productService;

    @Override
    public Product find(Long id) {
        logger.info("---------------调用商品服务");
        System.out.println("++++++调用商品服务+++++++++");
            Product product = productService.find(id);
            Product result = new Product();
            BeanUtils.copyProperties(product,result);
            result.setName(product.getName()+" data from port:"+port);

//        try {
//            TimeUnit.SECONDS.sleep(2);//睡眠2秒钟
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return result;
    }

    public List<Product> list(){
        return productService.list();
    }
}
