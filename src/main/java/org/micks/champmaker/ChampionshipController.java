package org.micks.champmaker;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ChampionshipController {

    @GetMapping
    public List<String> getChampionships() throws IOException, ExecutionException,InterruptedException {
        InputStream serviceAccount = new ClassPathResource("db3-champ-firebase-adminsdk-huz5y-f92bad101c.json").getInputStream();

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setDatabaseUrl("https://db3-champ-default-rtdb.firebaseio.com/")
                .build();


        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();

        CollectionReference championshipsCollection = db.collection("championschips");
        ApiFuture<QuerySnapshot> championshipsFuture = championshipsCollection.get();
        List<QueryDocumentSnapshot> championships = championshipsFuture.get().getDocuments();

        return championships.stream().map(ch -> ch.getString("champ_name")).collect(Collectors.toList());
    }
}
