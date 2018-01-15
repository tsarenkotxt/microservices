package com.griddynamics.product.service.impl;

import com.griddynamics.product.kafka.KeySet;
import com.griddynamics.product.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, KeySet key, String payload) {
        LOGGER.info("sending payload='{}' to topic='{}', key='{}'", payload, topic, key);
        kafkaTemplate.send(topic, key.toString(), payload);
    }

}
