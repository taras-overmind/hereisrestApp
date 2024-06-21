package com.project.tyrell.hereisrest.root;

import com.project.tyrell.hereisrest.shared.ModelDistanceBean;
import com.project.tyrell.hereisrest.shared.Utils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Stream;

@Service
public class RootModelService {

    public Stream<ModelDistanceBean> sortByDistance(double targetLat, double targetLon,
                                                    Stream<? extends RootModel> models) {
        return models
                .map(model -> new ModelDistanceBean(model, Utils.calculateDistance(targetLat, targetLon,
                        Double.parseDouble(model.latitude), Double.parseDouble(model.longitude))))
                .sorted(Comparator.comparingDouble(tuple -> tuple.distance));
    }
}
