package com.griddynamics.order.entity;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.util.UUID;

@Data
@UserDefinedType("order_address_type")
public class OrderAddress {

    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    @CassandraType(type = DataType.Name.VARCHAR)
    private String country;

    @CassandraType(type = DataType.Name.VARCHAR)
    private String city;

    @CassandraType(type = DataType.Name.VARCHAR)
    private String address;

    @CassandraType(type = DataType.Name.VARCHAR)
    private String phone;

    public OrderAddress() {
        this.id = UUID.randomUUID();
    }

}
