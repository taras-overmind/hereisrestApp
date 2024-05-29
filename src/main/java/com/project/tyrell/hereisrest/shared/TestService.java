package com.project.tyrell.hereisrest.shared;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TestService {

    private Firestore firestore = FirestoreClient.getFirestore();

    final String testServiceCollection = "test_users";

    public List<TestModel> getAllTestEntities() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection(testServiceCollection).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<TestModel> result = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            result.add(document.toObject(TestModel.class));
        }
        return result;
    }

    public TestModel getEntityById(String docId) throws ExecutionException, InterruptedException {
        Query query = firestore.collection(testServiceCollection).whereEqualTo("docId", docId);
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        if (!documents.isEmpty()) {
            // If a document is found, return it as a TestModel
            return documents.get(0).toObject(TestModel.class);
        } else {
            // If no document is found, return null or handle it as per your application's logic
            return null;
        }
    }

    public String createEntity(TestModel testModel) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = firestore.collection(testServiceCollection).document().set(testModel);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteEntityById(String docId) throws ExecutionException, InterruptedException {
        firestore.collection(testServiceCollection).whereEqualTo("docId", docId).get().get().forEach(
                x -> x.getReference().delete()
        );
        return "Deleted successfully";
    }
}
