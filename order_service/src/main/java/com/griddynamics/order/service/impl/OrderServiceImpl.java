package com.griddynamics.order.service.impl;

import com.griddynamics.order.entity.OrderInfo;
import com.griddynamics.order.mapper.JsonMapperWrapper;
import com.griddynamics.order.repository.OrderRepository;
import com.griddynamics.order.service.KafkaOrderService;
import com.griddynamics.order.service.OrderService;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.griddynamics.order.kafka.KeySet.*;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final KafkaOrderService kafkaOrderService;
    private final JsonMapperWrapper jsonMapper;

    private final String topic;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, KafkaOrderService kafkaOrderService,
                            JsonMapperWrapper jsonMapper,
                            @Value("${spring.kafka.consumer.topic.order}") String topic) {
        this.orderRepository = orderRepository;
        this.kafkaOrderService = kafkaOrderService;
        this.jsonMapper = jsonMapper;
        this.topic = topic;
    }

    @Override
    public OrderInfo save(OrderInfo orderInfo) {
        LOGGER.info("Save order: {}", orderInfo);
        OrderInfo saved = orderRepository.save(orderInfo);
        kafkaOrderService.send(topic, SAVE, jsonMapper.writeValue(saved));
        return saved;
    }

    @Override
    public OrderInfo update(UUID id, OrderInfo updated) {
        LOGGER.info("Update order, id: {}, {}", id, updated);
        OrderInfo orderInfo = orderRepository.findOne(id);
        orderInfo.setStatus(updated.getStatus());
        orderInfo.setTotalPrice(updated.getTotalPrice());
        orderInfo.setAddress(updated.getAddress());
        orderInfo.setItems(updated.getItems());
        OrderInfo saved = orderRepository.save(orderInfo);
        kafkaOrderService.send(topic, UPDATE, jsonMapper.writeValue(saved));
        return saved;
    }

    @Override
    public OrderInfo findOne(UUID id) {
        LOGGER.info("Find order, id: {}", id);
        return orderRepository.findOne(id);
    }

    @Override
    public List<OrderInfo> findAll() {
        LOGGER.info("Find all orders");
        Iterable<OrderInfo> orders = orderRepository.findAll();
        return IteratorUtils.toList(orders.iterator());
    }

    @Override
    public void delete(UUID id) {
        LOGGER.info("Delete order, id: {}", id);
        orderRepository.delete(id);
        kafkaOrderService.send(topic, DELETE, id.toString());
    }

}
