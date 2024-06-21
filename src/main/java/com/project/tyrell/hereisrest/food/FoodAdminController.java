package com.project.tyrell.hereisrest.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/admin/food")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class FoodAdminController {

    private final FoodService foodService;

    @Autowired
    public FoodAdminController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PutMapping("/")
    public String createFoodModel(@RequestBody FoodModel foodModel) throws ExecutionException, InterruptedException {
        return foodService.createFoodModel(foodModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateFoodModel(@PathVariable String id, @RequestBody FoodModel foodModel) {
        try {
            String updateTime = foodService.updateFoodModel(id, foodModel);
            return ResponseEntity.ok("Updated at: " + updateTime);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error updating food model: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodModel(@PathVariable String id) {
        try {
            foodService.deleteFoodModel(id);
            return ResponseEntity.ok("Deleted entity with ID: " + id);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error deleting food model: " + e.getMessage());
        }
    }
}
