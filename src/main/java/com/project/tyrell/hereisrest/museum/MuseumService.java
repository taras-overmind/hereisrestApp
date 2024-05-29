package com.project.tyrell.hereisrest.museum;

import com.project.tyrell.hereisrest.firestore.FirestoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class MuseumService {

    private final FirestoreDAO firestoreDAO;

    @Autowired
    public MuseumService(final FirestoreDAO firestoreDAO) {
        this.firestoreDAO = firestoreDAO;
    }

    private static final String MUSEUM_COLLECTION_NAME = "museumplace";

    public List<MuseumModel> getMuseumPlaceEntities() throws ExecutionException, InterruptedException {
        return firestoreDAO.getEntities(MUSEUM_COLLECTION_NAME, MuseumModel.class, "Kyiv");
    }

    public List<MuseumModel> getFilteredMuseumPlaces(
            MuseumFilterBody museumFilterBody,
            long offset)
            throws ExecutionException, InterruptedException {

        return getMuseumPlaceEntities().stream()
                .sorted(Comparator.comparingInt(o -> -o.getRelevancyRating(o, museumFilterBody)))
                .skip(offset * 10)
                .limit(10)
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

    public MuseumModel getMuseumModelById(final String id) throws ExecutionException, InterruptedException {
        return firestoreDAO.getEntityById(MUSEUM_COLLECTION_NAME, MuseumModel.class, id);
    }
}
