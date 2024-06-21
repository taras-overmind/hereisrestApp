package com.project.tyrell.hereisrest.museum;

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
public class MuseumService {

    private static final String MUSEUM_COLLECTION_NAME = "museumplace";

    private static final Logger logger = LoggerFactory.getLogger(MuseumService.class);

    private final RootModelService rootModelService;

    private final FirestoreDAO firestoreDAO;

    @Autowired
    public MuseumService(FirestoreDAO firestoreDAO, RootModelService rootModelService) {
        this.firestoreDAO = firestoreDAO;
        this.rootModelService = rootModelService;
    }

    public List<MuseumModel> getMuseumPlaceEntities() throws ExecutionException, InterruptedException {
        return firestoreDAO.getEntities(MUSEUM_COLLECTION_NAME, MuseumModel.class, "Kyiv");
    }

    public List<MuseumModel> getFilteredMuseumPlaces(
            MuseumFilterBody museumFilterBody,
            long offset)
            throws ExecutionException, InterruptedException {

        return getMuseumPlaceEntities().stream()
                .filter(x -> x.matchesFilter(x, museumFilterBody))
                .sorted(Comparator.comparingInt(o -> -o.getRelevancyRating(o, museumFilterBody)))
                .skip(offset * 10)
                .limit(10)
                .toList();
    }

    public MuseumModel getMuseumModelById(final String id) throws ExecutionException, InterruptedException {
        return firestoreDAO.getEntityById(MUSEUM_COLLECTION_NAME, MuseumModel.class, id);
    }

    public MuseumModel getFoodModelByName(String name) throws ExecutionException, InterruptedException {
        logger.info("Fetching museum model by name: {}", name);
        return firestoreDAO.getFoodModelByFieldValue(MUSEUM_COLLECTION_NAME, MuseumModel.class, "name", name);
    }

    public List<ModelDistanceBean> getFilteredMuseumModelsWithDistance(
            final MuseumFilterBody museumFilterBody,
            final long offset,
            final Double latitude,
            final Double longitude
    ) throws ExecutionException, InterruptedException {
        logger.info("Filtering museum models with filter body: {}, offset: {}, latitude: {}, longitude: {}",
                museumFilterBody, offset, latitude, longitude);
        return rootModelService.sortByDistance(latitude, longitude,
                        getMuseumPlaceEntities().stream()
                                .filter(x -> x.matchesFilter(x, museumFilterBody))
                                .sorted(Comparator.comparingInt(o -> -o.getRelevancyRating(o, museumFilterBody)))
                                .skip(offset * 10)
                                .limit(10))
                .toList();
    }

    public String createMuseumModel(final MuseumModel museumModel) throws ExecutionException, InterruptedException {
        return firestoreDAO.createEntity(MUSEUM_COLLECTION_NAME, museumModel);
    }

    public String updateMuseumModel(final String id, final MuseumModel museumModel) throws ExecutionException, InterruptedException {
        return firestoreDAO.updateEntity(MUSEUM_COLLECTION_NAME, id, museumModel);
    }

    public void deleteMuseumModel(final String id) throws ExecutionException,
            InterruptedException {
        firestoreDAO.deleteEntity(MUSEUM_COLLECTION_NAME, id);
    }
}
