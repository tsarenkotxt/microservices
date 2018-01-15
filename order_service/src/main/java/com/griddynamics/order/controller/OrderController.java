package com.griddynamics.order.controller;

import com.griddynamics.order.entity.OrderInfo;
import com.griddynamics.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/")
    public List<OrderInfo> getAll() {
        return orderService.findAll();
    }

    @GetMapping(value = "/{id}")
    public OrderInfo getOne(@PathVariable UUID id) {
        return orderService.findOne(id);
    }

    @PostMapping(value = "/")
    public OrderInfo create(@RequestBody OrderInfo orderInfo) {
        return orderService.save(orderInfo);
    }

    @PutMapping(value = "/{id}")
    public OrderInfo update(@PathVariable UUID id, @RequestBody OrderInfo orderInfo) {
        return orderService.update(id, orderInfo);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable UUID id) {
        orderService.delete(id);
    }

}
