package com.project.tyrell.hereisrest.park;

import com.project.tyrell.hereisrest.firestore.FirestoreDAO;
import com.project.tyrell.hereisrest.museum.MuseumModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ParkService {

    private final FirestoreDAO firestoreDAO;

    @Autowired
    public ParkService(FirestoreDAO firestoreDAO) {
        this.firestoreDAO = firestoreDAO;
    }

    private static final String PARK_COLLECTION_NAME = "parkplace";

    public List<ParkModel> getParkModelEntities() throws ExecutionException, InterruptedException {
        return firestoreDAO.getEntities(PARK_COLLECTION_NAME, ParkModel.class, "Kyiv");
    }

    public String createParkModel(ParkModel parkModel) throws ExecutionException, InterruptedException {
        return firestoreDAO.createEntity(PARK_COLLECTION_NAME, parkModel);
    }

    public List<ParkModel> getFilteredParkPlaces(
            ParkFilterBody parkFilterBody,
            long offset)
            throws ExecutionException, InterruptedException {
        return getParkModelEntities().stream()
                .sorted(Comparator.comparingInt(o -> -o.getRelevancyRating(o, parkFilterBody)))
                .skip(offset * 10)
                .limit(10)
                .toList();

    }

    public ParkModel getParkModelById(
            String id) throws ExecutionException, InterruptedException {
        return firestoreDAO.getEntityById(PARK_COLLECTION_NAME, ParkModel.class, id);
    }

    public String updateMuseumModel(final String id, final MuseumModel museumModel) throws ExecutionException, InterruptedException {
        return firestoreDAO.updateEntity(PARK_COLLECTION_NAME, id, museumModel);
    }

    public void deleteMuseumModel(final String id) throws ExecutionException,
            InterruptedException {
        firestoreDAO.deleteEntity(PARK_COLLECTION_NAME, id);
    }
}
