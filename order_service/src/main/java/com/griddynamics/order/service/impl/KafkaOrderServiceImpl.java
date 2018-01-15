package com.griddynamics.order.service.impl;

import com.griddynamics.order.kafka.KeySet;
import com.griddynamics.order.service.KafkaOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderServiceImpl implements KafkaOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaOrderServiceImpl.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaOrderServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, KeySet key, String payload) {
        LOGGER.info("sending payload='{}' to topic='{}', key='{}'", payload, topic, key);
        kafkaTemplate.send(topic, key.toString(), payload);
    }

}
