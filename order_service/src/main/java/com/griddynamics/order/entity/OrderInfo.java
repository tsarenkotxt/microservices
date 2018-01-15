package com.griddynamics.order.entity;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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

    @CassandraType(type = DataType.Name.UDT, userTypeName = "order_address_type")
    private OrderAddress address;

    @CassandraType(type = DataType.Name.UDT, userTypeName = "order_item_type")
    private List<OrderItem> items;

    public OrderInfo() {
        this.id = UUID.randomUUID();
    }

}
