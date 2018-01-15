package com.griddynamics.consumer.kafka;

import com.griddynamics.consumer.domain.OrderInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.consumer.topic.order}")
    public void receiveProduct(ConsumerRecord<KeySet, OrderInfo> consumerRecord) {
        LOGGER.info("Received topic={}, key={}, Order={}", consumerRecord.topic(), consumerRecord.key(), consumerRecord.value());
    }

}
