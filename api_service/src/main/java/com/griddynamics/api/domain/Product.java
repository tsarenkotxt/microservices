package com.griddynamics.api.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Product {

    private UUID id;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    public Product() {
        this.id = UUID.randomUUID();
    }

}
