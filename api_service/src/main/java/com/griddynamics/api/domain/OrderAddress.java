package com.griddynamics.api.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderAddress {

    private UUID id;
    private String country;
    private String city;
    private String address;
    private String phone;

    public OrderAddress() {
        this.id = UUID.randomUUID();
    }

}
