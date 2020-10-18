package com.adidas.email.subscription;

import com.adidas.email.gmail.GmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionListener {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionListener.class);

    private final GmailService gmailService;

    public SubscriptionListener(GmailService gmailService) {
        this.gmailService = gmailService;
    }

    @KafkaListener(topics = "subscription")
    public void listen(Subscription subscription) {
        logger.info("Received Message: {}", subscription);
        try {
            gmailService.send(subscription);
        } catch (Exception e) {
            logger.warn("Email wasn't sent for Subscription: {}", subscription, e);
        }
    }
}
