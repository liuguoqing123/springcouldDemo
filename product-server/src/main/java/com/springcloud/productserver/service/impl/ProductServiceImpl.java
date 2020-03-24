package com.springcloud.productserver.service.impl;

import com.springcloud.productserver.mapper.ProductMapper;
import com.springcloud.productapi.entity.Product;
import com.springcloud.productserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> list() {
        return productMapper.list();
    }

    @Override
    public Product find(Long id) {

        return productMapper.find(id);
    }
}
