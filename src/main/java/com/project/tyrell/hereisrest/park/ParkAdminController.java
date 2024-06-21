package com.project.tyrell.hereisrest.park;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/admin/park")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class ParkAdminController {

    private final ParkService parkService;

    @Autowired
    public ParkAdminController(ParkService parkService) {
        this.parkService = parkService;
    }

    @PutMapping("")
    public String createParkModel(@RequestBody ParkModel parkModel) throws ExecutionException, InterruptedException {
        return parkService.createParkModel(parkModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateParkModel(@PathVariable String id, @RequestBody ParkModel parkModel) {
        try {
            String updateTime = parkService.updateParkModel(id, parkModel);
            return ResponseEntity.ok("Updated at: " + updateTime);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error updating park model: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParkModel(@PathVariable String id) {
        try {
            parkService.deleteParkModel(id);
            return ResponseEntity.ok("Deleted entity with ID: " + id);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error deleting park model: " + e.getMessage());
        }
    }
}