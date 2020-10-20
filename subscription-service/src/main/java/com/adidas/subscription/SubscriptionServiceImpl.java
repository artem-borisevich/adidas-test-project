package com.adidas.subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
class SubscriptionServiceImpl implements SubscriptionService {

    private final Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionProducer subscriptionProducer;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, SubscriptionProducer subscriptionProducer) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionProducer = subscriptionProducer;
    }

    @Override
    public Subscription save(Subscription subscription) {
        subscription.setCreatedDate(LocalDateTime.now());
        Subscription createdSubscription = subscriptionRepository.save(subscription);
        logger.info("New Subscription has been created: {}", createdSubscription);
        subscriptionProducer.produce(createdSubscription);
        return createdSubscription;
    }
}
