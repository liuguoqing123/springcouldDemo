package com.springcloud.productserver.mapper;


import com.springcloud.productapi.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductMapper2 {

    private Map<Long, Product> map = new HashMap<Long, Product>();

   public ProductMapper2() {

        Product product1 = new Product(1L, "小米手机", 1999, 10);
        Product product2 = new Product(2L, "华为手机", 2999, 10);
        Product product3 = new Product(3L, "三星手机", 3999, 10);
        Product product4 = new Product(4L, "苹果手机", 4999, 10);
        Product product5 = new Product(5L, "锤子手机", 5999, 10);

        map.put(product1.getId(), product1);
        map.put(product2.getId(), product2);
        map.put(product3.getId(), product3);
        map.put(product4.getId(), product4);
        map.put(product5.getId(), product5);
    }

    public List<Product> list(){
        return new ArrayList<>(map.values());

    }

    public Product find(Long id){
        return map.get(id);
    }
}
