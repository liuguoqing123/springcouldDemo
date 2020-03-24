package com.springcloud.orderserver.service;

import com.springcloud.orderserver.entity.Order;

public interface OrderService {

    public Order save(Long userId,Long productId);
}
