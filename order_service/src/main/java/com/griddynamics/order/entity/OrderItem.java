package com.griddynamics.order.entity;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@UserDefinedType("order_item_type")
public class OrderItem {

    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    @Column("product_id")
    @CassandraType(type = DataType.Name.UUID)
    private UUID productId;

    @Column("amount")
    @CassandraType(type = DataType.Name.INT)
    private int amount;

    @Column("price")
    @CassandraType(type = DataType.Name.DECIMAL)
    private BigDecimal price;

    public OrderItem() {
        this.id = UUID.randomUUID();
        this.productId = UUID.randomUUID();
    }

}
