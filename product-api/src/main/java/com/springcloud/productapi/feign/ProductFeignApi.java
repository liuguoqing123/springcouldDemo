package com.springcloud.productapi.feign;


import com.springcloud.productapi.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-server")
public interface ProductFeignApi {

    @RequestMapping("/find")
    public Product find(@RequestParam("id") Long id);
}
