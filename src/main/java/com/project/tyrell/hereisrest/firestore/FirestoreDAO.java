package com.project.tyrell.hereisrest.firestore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.project.tyrell.hereisrest.shared.RootModel;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreDAO {

    private final Firestore firestore = FirestoreClient.getFirestore();

    private static final String CITY_FIELD = "city";

    public <T extends RootModel> List<T> getEntities(
            final String collectionName,
            @Nonnull Class<T> valueType,
            final String city) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection(collectionName)
                .whereEqualTo(CITY_FIELD, city).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<T> result = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            result.add(document.toObject(valueType));
            result.get(result.size() - 1).id = document.getId();
        }
        return result;
    }

    public <T extends RootModel> T getEntityById(
            final String collectionName,
            @Nonnull final Class<T> valueType,
            final String id) throws ExecutionException, InterruptedException {
        DocumentReference document = firestore.collection(collectionName).document(id);
        ApiFuture<DocumentSnapshot> future = document.get();
        DocumentSnapshot documentSnapshot = future.get();
        if (!documentSnapshot.exists()) {
            return null;
        }
        T result = documentSnapshot.toObject(valueType);
        assert result != null;
        result.id=documentSnapshot.getId();
        return result;
    }

    public <T extends RootModel> T getFoodModelByFieldValue(
            final String collectionName,
            @Nonnull final Class<T> valueType,
            final String field,
            final Object value) throws ExecutionException, InterruptedException {
        Query query = firestore.collection(collectionName).whereEqualTo(field, value);
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        if (documents.isEmpty()) {
            return null;
        }
        T result = documents.get(0).toObject(valueType);
        result.id = documents.get(0).getId();
        return result;
    }

    public String createEntity(final String collectionName, RootModel model) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = firestore.collection(collectionName).document().set(model);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String updateEntity(final String collectionName, final String id, RootModel model) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = firestore.collection(collectionName).document(id);
        ApiFuture<WriteResult> future = documentReference.set(model, SetOptions.merge());
        return future.get().getUpdateTime().toString();
    }

    public void deleteEntity(final String collectionName, final String id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = firestore.collection(collectionName).document(id);
        ApiFuture<WriteResult> future = documentReference.delete();
        future.get();
    }

}
