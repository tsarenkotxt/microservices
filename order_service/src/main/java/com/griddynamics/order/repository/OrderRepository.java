package com.griddynamics.order.repository;

import com.griddynamics.order.entity.OrderInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<OrderInfo, UUID> {

}
