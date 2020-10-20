package com.adidas.subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionProducer {

    private final Logger logger = LoggerFactory.getLogger(SubscriptionProducer.class);

    @Value("${service.email.topic}")
    private String topic;

    private final KafkaTemplate<Long, Subscription> kafkaTemplate;

    public SubscriptionProducer(KafkaTemplate<Long, Subscription> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async("threadPoolTaskExecutor")
    public void produce(Subscription subscription) {
        kafkaTemplate.send(topic, subscription.getNewsletterId(), subscription);
        logger.info("Produced message: {}", subscription);
    }
}
