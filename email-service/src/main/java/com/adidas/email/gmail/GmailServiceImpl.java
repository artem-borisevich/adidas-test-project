package com.adidas.email.gmail;

import com.adidas.email.subscription.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class GmailServiceImpl implements GmailService {

    private static final Logger logger = LoggerFactory.getLogger(GmailServiceImpl.class);

    @Value("${email.subject}")
    private String subject;

    private final Session session;

    public GmailServiceImpl(Session session) {
        this.session = session;
    }

    @Override
    public void send(Subscription subscription) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(subscription.getEmail()));
        message.setSubject(subject);
        message.setText(getText(subscription.getFirstName(), subscription.getNewsletterId()));
        Transport.send(message);
        logger.info("Message sent successfully: {}", subscription);
    }

    public String getText(String firstName, Long newsletterId) {
        return "Hello "
                .concat(firstName)
                .concat(", you have been subscribed to NewsletterId: ")
                .concat(newsletterId.toString());
    }
}
