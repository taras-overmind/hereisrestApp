package com.project.tyrell.hereisrest.food;

import java.util.Comparator;

import com.project.tyrell.hereisrest.firestore.FirestoreDAO;
import com.project.tyrell.hereisrest.shared.ModelDistanceBean;
import com.project.tyrell.hereisrest.shared.RootModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FoodService {

    private static final Logger logger = LoggerFactory.getLogger(FoodService.class);

    private final FirestoreDAO firestoreDAO;

    private final RootModelService rootModelService;

    @Autowired
    public FoodService(FirestoreDAO firestoreDAO, RootModelService rootModelService) {
        this.firestoreDAO = firestoreDAO;
        this.rootModelService = rootModelService;
    }

    private static final String FOOD_MODEL_COLLECTION_NAME = "foodplace";

    public List<FoodModel> getFoodModelEntities() throws ExecutionException, InterruptedException {
        logger.info("Fetching food model entities");
        return firestoreDAO.getEntities(FOOD_MODEL_COLLECTION_NAME, FoodModel.class, "Kyiv");
    }

    public List<ModelDistanceBean> getFilteredFoodModelsWithDistance(
            final FoodFilterBody foodFilterBody,
            final long offset,
            final Double latitude,
            final Double longitude
    ) throws ExecutionException, InterruptedException {
        logger.info("Filtering food models with filter body: {}, offset: {}, latitude: {}, longitude: {}", foodFilterBody, offset, latitude, longitude);
        return rootModelService.sortByDistance(latitude, longitude,
                        getFoodModelEntities().stream()
                                .filter(x -> x.matchesFilter(x, foodFilterBody))
                                .sorted(Comparator.comparingInt(o -> -o.getRelevancyRating(o, foodFilterBody)))
                                .skip(offset * 10)
                                .limit(10))
                .toList();
    }

    public List<FoodModel> getFilteredFoodModels(
            final FoodFilterBody foodFilterBody,
            final long offset
    ) throws ExecutionException, InterruptedException {
        logger.info("Filtering food models with filter body: {}, offset: {}", foodFilterBody, offset);
        return getFoodModelEntities().stream()
                .filter(x -> x.matchesFilter(x, foodFilterBody))
                .sorted(Comparator.comparingInt(o -> -o.getRelevancyRating(o, foodFilterBody)))
                .skip(offset * 10)
                .limit(10)
                .toList();
    }

    public FoodModel getFoodModelByName(String name) throws ExecutionException, InterruptedException {
        logger.info("Fetching food model by name: {}", name);
        return firestoreDAO.getFoodModelByFieldValue(FOOD_MODEL_COLLECTION_NAME, FoodModel.class, "name", name);
    }

    public FoodModel getFoodModelById(String id) throws ExecutionException, InterruptedException {
        logger.info("Fetching food model by ID: {}", id);
        return firestoreDAO.getEntityById(FOOD_MODEL_COLLECTION_NAME, FoodModel.class, id);
    }

    public String createFoodModel(FoodModel foodModel) throws ExecutionException, InterruptedException {
        logger.info("Creating food model: {}", foodModel);
        return firestoreDAO.createEntity(FOOD_MODEL_COLLECTION_NAME, foodModel);
    }

    public String updateFoodModel(final String id, final FoodModel foodModel) throws ExecutionException, InterruptedException {
        logger.info("Updating food model with ID: {}, new data: {}", id, foodModel);
        return firestoreDAO.updateEntity(FOOD_MODEL_COLLECTION_NAME, id, foodModel);
    }

    public void deleteFoodModel(final String id) throws ExecutionException, InterruptedException {
        logger.info("Deleting food model with ID: {}", id);
        firestoreDAO.deleteEntity(FOOD_MODEL_COLLECTION_NAME, id);
    }
}
