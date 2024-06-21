package com.project.tyrell.hereisrest.park;

import com.project.tyrell.hereisrest.shared.ModelDistanceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/park")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class ParkController {

    private final ParkService parkService;

    @Autowired
    public ParkController(final ParkService parkService) {
        this.parkService = parkService;
    }

    @GetMapping("")
    public List<ParkModel> getAll() throws ExecutionException, InterruptedException {
        return parkService.getParkModelEntities();
    }

    @GetMapping("/sorted/{page}")
    public List<ParkModel> getSortedAndPaginated(ParkFilterBody parkFilterBody,
                                                   @PathVariable("page") Long offset
    ) throws ExecutionException, InterruptedException {
        return parkService.getFilteredParkPlaces(parkFilterBody, Optional.ofNullable(offset).orElse(0L));
    }

    @GetMapping("/distance")
    public List<ModelDistanceBean> getWithDistance(ParkFilterBody parkFilterBody,
                                                   @RequestParam(value = "latitude") Double latitude,
                                                   @RequestParam(value = "longitude") Double longitude
    ) throws ExecutionException, InterruptedException {
        return parkService.getFilteredParkModelsWithDistance(parkFilterBody, 0L, latitude, longitude);
    }

    @GetMapping("/id/{id}")
    public ParkModel getFoodModelById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return parkService.getParkModelById(id);
    }

    @GetMapping("/name/{name}")
    public ParkModel getFoodModelByName(@PathVariable("name") String name) throws ExecutionException,
            InterruptedException {
        return parkService.getFoodModelByName(name);
    }
}