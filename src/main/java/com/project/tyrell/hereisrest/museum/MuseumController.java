package com.project.tyrell.hereisrest.museum;

import com.project.tyrell.hereisrest.shared.ModelDistanceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/museum")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class MuseumController {

    private final MuseumService museumService;

    @Autowired
    public MuseumController(final MuseumService museumService) {
        this.museumService = museumService;
    }

    @GetMapping("")
    public List<MuseumModel> getAll() throws ExecutionException, InterruptedException {
        return museumService.getMuseumPlaceEntities();
    }

    @GetMapping("/sorted/{page}")
    public List<MuseumModel> getSortedAndPaginated(@RequestParam MuseumFilterBody museumFilterBody,
                                                 @PathVariable("page") Long offset
    ) throws ExecutionException, InterruptedException {
        return museumService.getFilteredMuseumPlaces(museumFilterBody, Optional.ofNullable(offset).orElse(0L));
    }

    @GetMapping("/distance")
    public List<ModelDistanceBean> getWithDistance(MuseumFilterBody museumFilterBody,
                                                   @RequestParam(value = "latitude") Double latitude,
                                                   @RequestParam(value = "longitude") Double longitude
    ) throws ExecutionException, InterruptedException {
        return museumService.getFilteredMuseumModelsWithDistance(museumFilterBody, 0L, latitude, longitude);
    }

    @GetMapping("/id/{id}")
    public MuseumModel getFoodModelById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return museumService.getMuseumModelById(id);
    }

    @GetMapping("/name/{name}")
    public MuseumModel getFoodModelByName(@PathVariable("name") String name) throws ExecutionException,
            InterruptedException {
        return museumService.getFoodModelByName(name);
    }
}
