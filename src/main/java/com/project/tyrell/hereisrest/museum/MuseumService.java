package com.project.tyrell.hereisrest.museum;

import com.project.tyrell.hereisrest.firestore.FirestoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return firestoreDAO.getEntities(MUSEUM_COLLECTION_NAME, MuseumModel.class);
    }

    public List<MuseumModel> getFilteredMuseumPlaces(
            MuseumFilterBody museumFilterBody,
            long offset)
            throws ExecutionException, InterruptedException {

        return getMuseumPlaceEntities().stream()
                .filter(m -> m.matchesFilter(m, museumFilterBody))
                .skip(offset * 10)
                .limit(10)
                .toList();
    }

    public String createMuseumModel(final MuseumModel museumModel) throws ExecutionException, InterruptedException {
        return firestoreDAO.createEntity(MUSEUM_COLLECTION_NAME, museumModel);
    }

    public MuseumModel getMuseumModelById(final String id) throws ExecutionException, InterruptedException {
        return firestoreDAO.getEntityById(MUSEUM_COLLECTION_NAME, MuseumModel.class, id);
    }
}
