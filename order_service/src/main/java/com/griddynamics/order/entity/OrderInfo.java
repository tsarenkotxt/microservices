package com.griddynamics.order.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table("order_info")
public class OrderInfo {

    @PrimaryKey
    private UUID id;

    @Column("user_id")
    private UUID userId;

    @Column("status")
    private String status;

    @Column("total_price")
    private BigDecimal totalPrice;

    @Column("address")
    private OrderAddress address;

    @Column("items")
    private List<OrderItem> items;

    public OrderInfo() {
        this.id = UUID.randomUUID();
    }

}
