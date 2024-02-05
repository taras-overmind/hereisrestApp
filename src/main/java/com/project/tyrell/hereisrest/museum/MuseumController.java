package com.project.tyrell.hereisrest.museum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class MuseumController {
    private final MuseumService museumService;
    @Autowired
    public MuseumController(MuseumService museumService) {
        this.museumService = museumService;
    }

    @GetMapping("/getMuseumModels")
    public List<MuseumModel> getAllMuseumModels() throws ExecutionException, InterruptedException {
        return museumService.getMuseumPlaceEntities();
    }
    @PostMapping("/createMuseumModel")
    public String createMuseumModel(@RequestBody MuseumModel museumModel ) throws ExecutionException, InterruptedException {
        return museumService.createMuseumModel(museumModel);
    }
    @PostMapping("/getFilteredMuseumModels")
    public List<MuseumModel> getMuseumModelsByFilter(@RequestBody MuseumFilterBody museumFilterBody) throws ExecutionException, InterruptedException {
        return museumService.getFilteredMuseumPlaces(museumFilterBody);
    }
    @GetMapping("/getMuseumModelById")
    public MuseumModel getMuseumModelById(@RequestParam String id) throws ExecutionException, InterruptedException {
        return museumService.getMuseumModelById(id);
    }
}
