package com.springcloud.productserver.controller;

import com.springcloud.productapi.entity.Product;
import com.springcloud.productserver.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/productServer")
//@RefreshScope
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${server.port}")
    private String port;

//    @Value("${env}")
//    private String env;

//    @RequestMapping("/testEnv")
//    public String testEnv(){
//        return "config配置中心："+env;
//    }


    @RequestMapping("/findById")
    public Product findById(@RequestParam("id") Long id){
        Product product = productService.find(id);

        Product result = new Product();
        BeanUtils.copyProperties(product,result);
        result.setName(product.getName()+" data from port:"+port);
        return result;
    }

    @RequestMapping("/list")
    public List<Product> list(){
        return productService.list();
    }
}
