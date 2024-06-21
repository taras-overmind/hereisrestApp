package com.project.tyrell.hereisrest.museum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/admin/museum")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class MuseumAdminController {

    private final MuseumService museumService;

    @Autowired
    public MuseumAdminController(MuseumService museumService) {
        this.museumService = museumService;
    }

    @PutMapping("/")
    public String createMuseumModel(@RequestBody MuseumModel museumModel) throws ExecutionException, InterruptedException {
        return museumService.createMuseumModel(museumModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateMuseumModel(@PathVariable String id, @RequestBody MuseumModel museumModel) {
        try {
            String updateTime = museumService.updateMuseumModel(id, museumModel);
            return ResponseEntity.ok("Updated at: " + updateTime);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error updating museum model: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMuseumModel(@PathVariable String id) {
        try {
            museumService.deleteMuseumModel(id);
            return ResponseEntity.ok("Deleted entity with ID: " + id);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error deleting museum model: " + e.getMessage());
        }
    }
}