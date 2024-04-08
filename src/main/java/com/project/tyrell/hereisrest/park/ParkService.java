package com.project.tyrell.hereisrest.park;

import com.project.tyrell.hereisrest.firestore.FirestoreDAO;
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
//        ApiFuture<QuerySnapshot> future = firestore.collection(parkModelCollection).get();
//        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
//        List<ParkModel> result = new ArrayList<>();
//        for (QueryDocumentSnapshot document : documents) {
//            result.add(document.toObject(ParkModel.class));
//            result.get(result.size() - 1).id = document.getId();
//        }
//        return result;
        return firestoreDAO.getEntities(PARK_COLLECTION_NAME, ParkModel.class);
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
}
