package com.adidas.email.gmail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Configuration
public class GmailConfig {

    @Value("${email.from}")
    private String from;

    @Value("${email.password}")
    private String password;

    @Bean
    @ConfigurationProperties(prefix = "gmail")
    public Properties gmailProperties() {
        return new Properties();
    }

    @Bean
    public Session session() {
        return Session.getDefaultInstance(gmailProperties(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
    }
}
