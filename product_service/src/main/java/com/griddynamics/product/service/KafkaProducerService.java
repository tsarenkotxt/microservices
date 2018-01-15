package com.griddynamics.product.service;

import com.griddynamics.product.kafka.KeySet;

public interface KafkaProducerService {

    void send(String topic, KeySet key, String payload);

}
