package com.adidas.subscription.controllers;

import com.adidas.subscription.Subscription;
import com.adidas.subscription.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public Subscription save(@Valid @RequestBody Subscription subscription) {
        logger.info("New request has been retrieved: {}", subscription);
        return subscriptionService.save(subscription);
    }
}
