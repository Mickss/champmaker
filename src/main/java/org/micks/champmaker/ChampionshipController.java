package org.micks.champmaker;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ChampionshipController {

    @GetMapping
    public List<Championship> getChampionships() throws IOException, ExecutionException,InterruptedException {

        Firestore db = FirestoreClient.getFirestore();
        CollectionReference championshipsCollection = db.collection("championships");
        ApiFuture<QuerySnapshot> championshipsFuture = championshipsCollection.get();
        List<QueryDocumentSnapshot> championships = championshipsFuture.get().getDocuments();

        return championships.stream().map(doc -> {
           return new Championship(doc.getString("name"));
//            return doc.getString("champ_name");
        }).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createChampionship(@RequestBody Championship championship) {
        System.out.println("działa: " + championship.getName());
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference championshipsCollection = db.collection("championships");
        championshipsCollection.add(championship);
    }
}
