package com.springcloud.productserver.mapper;

import com.springcloud.productapi.entity.Product;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Repository
@Mapper
public interface ProductMapper {
    public List<Product> list();

    public Product find(Long id);
}
