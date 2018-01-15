package com.griddynamics.product.domain;

import lombok.Data;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Table
public class Product {

    @PrimaryKey
    private UUID id;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    public Product() {
        this.id = UUID.randomUUID();
    }

}
