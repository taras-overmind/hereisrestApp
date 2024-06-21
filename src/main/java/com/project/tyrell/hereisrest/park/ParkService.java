package com.project.tyrell.hereisrest.park;

import com.project.tyrell.hereisrest.firestore.FirestoreDAO;
import com.project.tyrell.hereisrest.shared.ModelDistanceBean;
import com.project.tyrell.hereisrest.root.RootModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ParkService {

    private static final String PARK_COLLECTION_NAME = "parkplace";

    private static final Logger logger = LoggerFactory.getLogger(ParkService.class);

    private final FirestoreDAO firestoreDAO;

    private final RootModelService rootModelService;

    @Autowired
    public ParkService(FirestoreDAO firestoreDAO, RootModelService rootModelService) {
        this.firestoreDAO = firestoreDAO;
        this.rootModelService = rootModelService;
    }

    public List<ParkModel> getParkModelEntities() throws ExecutionException, InterruptedException {
        logger.info("Fetching all park model entities.");
        return firestoreDAO.getEntities(PARK_COLLECTION_NAME, ParkModel.class, "Kyiv");
    }

    public List<ParkModel> getFilteredParkPlaces(
            ParkFilterBody parkFilterBody,
            long offset)
            throws ExecutionException, InterruptedException {
        logger.info("Filtering park places with filter body: {}, offset: {}", parkFilterBody, offset);
        return getParkModelEntities().stream()
                .sorted(Comparator.comparingInt(o -> -o.getRelevancyRating(o, parkFilterBody)))
                .skip(offset * 10)
                .limit(10)
                .toList();
    }

    public List<ModelDistanceBean> getFilteredParkModelsWithDistance(
            final ParkFilterBody parkFilterBody,
            final long offset,
            final Double latitude,
            final Double longitude
    ) throws ExecutionException, InterruptedException {
        logger.info("Filtering park models with filter body: {}, offset: {}, latitude: {}, longitude: {}", parkFilterBody, offset, latitude, longitude);
        return rootModelService.sortByDistance(latitude, longitude,
                        getParkModelEntities().stream()
                                .filter(x -> x.matchesFilter(x, parkFilterBody))
                                .sorted(Comparator.comparingInt(o -> -o.getRelevancyRating(o, parkFilterBody)))
                                .skip(offset * 10)
                                .limit(10))
                .toList();
    }

    public ParkModel getParkModelById(String id) throws ExecutionException, InterruptedException {
        logger.info("Fetching park model by ID: {}", id);
        return firestoreDAO.getEntityById(PARK_COLLECTION_NAME, ParkModel.class, id);
    }

    public ParkModel getFoodModelByName(String name) throws ExecutionException, InterruptedException {
        logger.info("Fetching park model by name: {}", name);
        return firestoreDAO.getFoodModelByFieldValue(PARK_COLLECTION_NAME, ParkModel.class, "name", name);
    }

    public String createParkModel(ParkModel parkModel) throws ExecutionException, InterruptedException {
        logger.info("Creating park model: {}", parkModel);
        return firestoreDAO.createEntity(PARK_COLLECTION_NAME, parkModel);
    }

    public String updateParkModel(final String id, final ParkModel parkModel) throws ExecutionException,
            InterruptedException {
        logger.info("Updating park model with ID: {}", id);
        return firestoreDAO.updateEntity(PARK_COLLECTION_NAME, id, parkModel);
    }

    public void deleteParkModel(final String id) throws ExecutionException,
            InterruptedException {
        logger.info("Deleting park model with ID: {}", id);
        firestoreDAO.deleteEntity(PARK_COLLECTION_NAME, id);
    }
}