package com.griddynamics.consumer.kafka;

import com.griddynamics.consumer.domain.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserConsumer.class);

    @KafkaListener(topics = "${spring.kafka.consumer.topic.user}")
    public void receiveUser(ConsumerRecord<KeySet, User> consumerRecord) {
        LOGGER.info("Received topic={}, key={}, User={}", consumerRecord.topic(), consumerRecord.key(), consumerRecord.value());
    }

}
