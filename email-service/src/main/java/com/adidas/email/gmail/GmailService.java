package com.adidas.email.gmail;

import com.adidas.email.subscription.Subscription;

import javax.mail.MessagingException;
import java.io.IOException;

public interface GmailService {

    void send(Subscription subscription) throws MessagingException, IOException;
}
