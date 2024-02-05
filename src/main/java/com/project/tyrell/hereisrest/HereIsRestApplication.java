package com.project.tyrell.hereisrest;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class HereIsRestApplication {

    public static void main(String[] args) throws IOException {

        InputStream serviceAccountStream = HereIsRestApplication.class.getResourceAsStream("/service/serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .build();

        FirebaseApp.initializeApp(options);

        SpringApplication.run(HereIsRestApplication.class, args);
    }

}
