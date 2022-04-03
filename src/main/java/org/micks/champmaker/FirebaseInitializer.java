package org.micks.champmaker;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class FirebaseInitializer {

    public static void initialize() throws IOException {
        InputStream serviceAccount = new ClassPathResource("/db3-champ-firebase-adminsdk-huz5y-f92bad101c.json").getInputStream();

        FirebaseOptions options = FirebaseOptions.builder()
//                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://db3-champ-default-rtdb.firebaseio.com/")
                .build();


        FirebaseApp.initializeApp(options);

    }
}

