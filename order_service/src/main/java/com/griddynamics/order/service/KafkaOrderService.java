package com.griddynamics.order.service;

import com.griddynamics.order.kafka.KeySet;

public interface KafkaOrderService {

    void send(String topic, KeySet key, String payload);

}
