package com.springcloud.productserver.service;

import com.springcloud.productapi.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> list();

    public Product find(Long id);
}
