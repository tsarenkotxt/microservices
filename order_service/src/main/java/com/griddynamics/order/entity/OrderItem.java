package com.griddynamics.order.entity;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Data
@UserDefinedType("order_item_type")
public class OrderItem {

    @CassandraType(type = Name.UUID)
    private UUID id;

    @Column("product_id")
    @CassandraType(type = Name.UUID)
    private UUID productId;

    @Column("amount")
    @CassandraType(type = Name.INT)
    private int amount;

    @Column("price")
    @CassandraType(type = Name.DECIMAL)
    private BigDecimal price;

    public OrderItem() {
        this.id = UUID.randomUUID();
        this.productId = UUID.randomUUID();
    }

}
