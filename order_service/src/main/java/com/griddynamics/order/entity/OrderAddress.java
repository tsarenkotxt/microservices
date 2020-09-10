package com.griddynamics.order.entity;

import java.util.UUID;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Data
@UserDefinedType("order_address_type")
public class OrderAddress {

    @CassandraType(type = Name.UUID)
    private UUID id;

    @CassandraType(type = Name.VARCHAR)
    private String country;

    @CassandraType(type = Name.VARCHAR)
    private String city;

    @CassandraType(type = Name.VARCHAR)
    private String address;

    @CassandraType(type = Name.VARCHAR)
    private String phone;

    public OrderAddress() {
        this.id = UUID.randomUUID();
    }

}
