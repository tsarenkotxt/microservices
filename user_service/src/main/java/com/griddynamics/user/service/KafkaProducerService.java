package com.griddynamics.user.service;

import com.griddynamics.user.kafka.KeySet;

public interface KafkaProducerService {

    void send(String topic, KeySet key, String payload);

}
