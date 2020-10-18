package com.adidas.email.gmail;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Configuration
public class GmailAuthConfig {

    @Value("${gmail.application_name}")
    private String applicationName;

    @Value("${gmail.user_id}")
    private String userId;

    @Value("${gmail.tokens_directory_path}")
    private String tokensDirectoryPath;

    @Value("${gmail.credentials_file_path}")
    private String credentialsFilePath;

    @Bean
    public JsonFactory jsonFactory() {
        return JacksonFactory.getDefaultInstance();
    }

    @Bean
    public NetHttpTransport netHttpTransport() throws GeneralSecurityException, IOException {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    @Bean
    public Credential credential() throws IOException, GeneralSecurityException {
        InputStream inputStream = getClass().getResourceAsStream(credentialsFilePath);
        if (inputStream == null) {
            throw new FileNotFoundException("Resource not found: " + credentialsFilePath);
        }

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory(), new InputStreamReader(inputStream));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow
                .Builder(netHttpTransport(), jsonFactory(), clientSecrets, Collections.singletonList(GmailScopes.GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(tokensDirectoryPath)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver
                .Builder()
                .setPort(8888)
                .build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize(userId);
    }

    @Bean
    public Gmail gmail() throws GeneralSecurityException, IOException {
        return new Gmail
                .Builder(netHttpTransport(), jsonFactory(), credential())
                .setApplicationName(applicationName)
                .build();
    }
}
