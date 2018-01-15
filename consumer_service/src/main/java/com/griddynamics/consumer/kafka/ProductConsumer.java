package com.griddynamics.consumer.kafka;

import com.griddynamics.consumer.domain.Product;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConsumer.class);

    @KafkaListener(topics = "${spring.kafka.consumer.topic.product}")
    public void receiveProduct(ConsumerRecord<KeySet, Product> consumerRecord) {
        LOGGER.info("Received topic={}, key={}, Product={}", consumerRecord.topic(), consumerRecord.key(), consumerRecord.value());
    }

}
