package com.griddynamics.consumer.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class OrderInfo {

    private UUID id;
    private UUID userId;
    private String status;
    private BigDecimal totalPrice;
    private OrderAddress address;
    private List<OrderItem> items;

    public OrderInfo() {
        this.id = UUID.randomUUID();
    }

}
