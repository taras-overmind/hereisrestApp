package com.project.tyrell.hereisrest.park;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class ParkController {
    private final ParkService parkService;

    @Autowired
    public ParkController(ParkService parkService) {
        this.parkService = parkService;
    }

    @GetMapping("/getParkModels")
    public List<ParkModel> getAllParkModels() throws ExecutionException, InterruptedException {
        return parkService.getParkModelEntities();
    }

    @PostMapping("/getFilteredParkModels")
    public List<ParkModel> getParkModelsByFilter(@RequestBody ParkFilterBody parkFilterBody,
                                                 @RequestParam(value = "offset", required=false) Long offset)
            throws ExecutionException, InterruptedException {
        return parkService.getFilteredParkPlaces(parkFilterBody, Optional.ofNullable(offset).orElse(0L));
    }

    @PostMapping("/createParkModel")
    public String createParkModel(@RequestBody ParkModel parkModel) throws ExecutionException, InterruptedException {
        return parkService.createParkModel(parkModel);
    }

    @GetMapping("/getParkModelById")
    public ParkModel getMuseumModelById(@RequestParam String id) throws ExecutionException, InterruptedException {
        return parkService.getParkModelById(id);
    }
}
