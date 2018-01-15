package com.griddynamics.api.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderItem {

    private UUID id;
    private UUID productId;
    private int amount;
    private BigDecimal price;

    public OrderItem() {
        this.id = UUID.randomUUID();
    }

}
