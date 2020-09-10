package com.griddynamics.product.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Table
public class Product {

    @PrimaryKey
    private UUID id;

    @Column("description")
    private String description;

    @Column("price")
    private BigDecimal price;

    @Column("image_url")
    private String imageUrl;

    public Product() {
        this.id = UUID.randomUUID();
    }

}
