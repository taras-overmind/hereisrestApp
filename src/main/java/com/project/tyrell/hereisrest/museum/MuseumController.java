package com.project.tyrell.hereisrest.museum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public String createMuseumModel(@RequestBody MuseumModel museumModel) throws ExecutionException, InterruptedException {
        return museumService.createMuseumModel(museumModel);
    }

    @PostMapping("/getFilteredMuseumModels")
    public List<MuseumModel> getMuseumModelsByFilter(@RequestBody MuseumFilterBody museumFilterBody,
                                                     @RequestParam(value = "offset", required = false) Long offset)
            throws ExecutionException, InterruptedException {
        return museumService.getFilteredMuseumPlaces(museumFilterBody, Optional.ofNullable(offset).orElse(0L));
    }

    @GetMapping("/getMuseumModelById")
    public MuseumModel getMuseumModelById(@RequestParam String id) throws ExecutionException, InterruptedException {
        return museumService.getMuseumModelById(id);
    }

    @PutMapping("museum/{id}")
    public ResponseEntity<String> updateMuseumModel(@PathVariable String id, @RequestBody MuseumModel museumModel) {
        try {
            String updateTime = museumService.updateMuseumModel(id, museumModel);
            return ResponseEntity.ok("Updated at: " + updateTime);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error updating museum model: " + e.getMessage());
        }
    }

    @DeleteMapping("museum/{id}")
    public ResponseEntity<String> deleteMuseumModel(@PathVariable String id) {
        try {
            museumService.deleteMuseumModel(id);
            return ResponseEntity.ok("Deleted entity with ID: " + id);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error deleting museum model: " + e.getMessage());
        }
    }
}
