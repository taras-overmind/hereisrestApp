package com.project.tyrell.hereisrest.food;

import com.project.tyrell.hereisrest.shared.ModelDistanceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class FoodController {
    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/getFoodModels")
    public List<FoodModel> getAllFoodModels() throws ExecutionException, InterruptedException {
        return foodService.getFoodModelEntities();
    }

    @PostMapping("/getFilteredFoodModels")
    public List<FoodModel> getFoodModelsByFilter(@RequestBody FoodFilterBody foodFilterBody,
                                                 @RequestParam(value = "offset", required = false) Long offset
    ) throws ExecutionException, InterruptedException {
        return foodService.getFilteredFoodModels(foodFilterBody, Optional.ofNullable(offset).orElse(0L));
    }

    @PostMapping("/getFilteredFoodModelsWithDistance")
    public List<ModelDistanceBean> getFoodModelsByFilter(@RequestBody FoodFilterBody foodFilterBody,
                                                         @RequestParam(value = "latitude") Double latitude,
                                                         @RequestParam(value = "longitude") Double longitude
    ) throws ExecutionException, InterruptedException {
        return foodService.getFilteredFoodModelsWithDistance(foodFilterBody, 0L, latitude, longitude);
    }


    @GetMapping("/getFoodModelById")
    public FoodModel getFoodModelById(@RequestParam String id) throws ExecutionException, InterruptedException {
        return foodService.getFoodModelById(id);
    }

    @GetMapping("/getFoodModelByName")
    public FoodModel getFoodModelByName(@RequestParam String name) throws ExecutionException, InterruptedException {
        return foodService.getFoodModelByName(name);
    }


    @PostMapping("/createFoodModel")
    public String createFoodModel(@RequestBody FoodModel foodModel) throws ExecutionException, InterruptedException {
        return foodService.createFoodModel(foodModel);
    }

    @PutMapping("food/{id}")
    public ResponseEntity<String> updateFoodModel(@PathVariable String id, @RequestBody FoodModel foodModel) {
        try {
            String updateTime = foodService.updateFoodModel(id, foodModel);
            return ResponseEntity.ok("Updated at: " + updateTime);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error updating food model: " + e.getMessage());
        }
    }

    @DeleteMapping("food/{id}")
    public ResponseEntity<String> deleteFoodModel(@PathVariable String id) {
        try {
            foodService.deleteFoodModel(id);
            return ResponseEntity.ok("Deleted entity with ID: " + id);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error deleting food model: " + e.getMessage());
        }
    }
}
