package com.griddynamics.api.service;

import com.griddynamics.api.domain.OrderInfo;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderInfo save(OrderInfo orderInfo);

    OrderInfo update(UUID id, OrderInfo orderInfo);

    OrderInfo findOne(UUID id);

    List<OrderInfo> findAll();

    void delete(UUID id);

}
