package com.project.tyrell.hereisrest.food;

import java.util.Comparator;

import com.project.tyrell.hereisrest.firestore.FirestoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FoodService {
    private final FirestoreDAO firestoreDAO;

    @Autowired
    public FoodService(FirestoreDAO firestoreDAO) {
        this.firestoreDAO = firestoreDAO;
    }

    private static final String FOOD_MODEL_COLLECTION_NAME = "foodplace";

    public List<FoodModel> getFoodModelEntities() throws ExecutionException, InterruptedException {
//        ApiFuture<QuerySnapshot> future = firestore.collection(FOOD_MODEL_COLLECTION_NAME).get();
//        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
//        List<FoodModel> result = new ArrayList<>();
//        for (QueryDocumentSnapshot document : documents) {
//            result.add(document.toObject(FoodModel.class));
//            result.get(result.size() - 1).id = document.getId();
//        }
//        return result;
        return firestoreDAO.getEntities(FOOD_MODEL_COLLECTION_NAME, FoodModel.class);
    }


    public List<FoodModel> getFilteredFoodModels(
            FoodFilterBody foodFilterBody) throws ExecutionException, InterruptedException {
        return getFoodModelEntities().stream()
                .filter(x -> x.matchesFilter(x, foodFilterBody))
                .sorted(Comparator.comparingInt(o -> -o.getRelevancyRating(o, foodFilterBody)))
                .toList();
    }

    public FoodModel getFoodModelByName(String name) throws ExecutionException, InterruptedException {
//        Query query = firestore.collection(FOOD_MODEL_COLLECTION_NAME).whereEqualTo("name", name);
//        ApiFuture<QuerySnapshot> future = query.get();
//        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
//        if (!documents.isEmpty()) {
//            FoodModel foodModel = documents.get(0).toObject(FoodModel.class);
//            foodModel.id = documents.get(0).getId();
//            return foodModel;
//        } else {
//            return null;
//        }
        return firestoreDAO.getFoodModelByFieldValue(FOOD_MODEL_COLLECTION_NAME, FoodModel.class, "name", name);
    }

    public FoodModel getFoodModelById(String id) throws ExecutionException, InterruptedException {
//        DocumentReference document = firestore.collection(FOOD_MODEL_COLLECTION_NAME).document(id);
//        ApiFuture<DocumentSnapshot> future = document.get();
//        DocumentSnapshot documentSnapshot = future.get();
//        if (documentSnapshot.exists()) {
//            FoodModel foodModel = documentSnapshot.toObject(FoodModel.class);
//            foodModel.id = documentSnapshot.getId();
//            return foodModel;
//        } else {
//            return null;
//        }
        return firestoreDAO.getEntityById(FOOD_MODEL_COLLECTION_NAME, FoodModel.class, id);
    }

    public String createFoodModel(FoodModel foodModel) throws ExecutionException, InterruptedException {
        return firestoreDAO.createEntity(FOOD_MODEL_COLLECTION_NAME, foodModel);
    }
}
