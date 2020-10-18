package com.adidas.email.gmail;

import com.adidas.email.subscription.Subscription;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class GmailServiceImpl implements GmailService {

    private static final Logger logger = LoggerFactory.getLogger(GmailServiceImpl.class);

    @Value("${gmail.user_id}")
    private String userId;

    @Value("${gmail.from}")
    private String from;

    @Value("${gmail.subject}")
    private String subject;

    private final Gmail gmail;

    private final Properties props;

    public GmailServiceImpl(Gmail gmail) {
        this.gmail = gmail;

        props = new Properties();
    }

    @Override
    public void send(Subscription subscription) throws MessagingException, IOException {
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(from));
        mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(subscription.getEmail()));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(getText(subscription.getFirstName(), subscription.getNewsletterId()));
        send(mimeMessage);
    }

    public void send(MimeMessage mimeMessage) throws IOException, MessagingException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        mimeMessage.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);

        message = gmail.users().messages().send(userId, message).execute();
        logger.info("Message has been sent: {}", message);
    }

    public String getText(String firstName, Long newsletterId) {
        return "Hello ".concat(firstName).concat(", you have been subscribed to NewsletterId: ".concat(newsletterId.toString()));
    }
}
