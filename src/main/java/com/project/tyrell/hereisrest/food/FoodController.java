package com.project.tyrell.hereisrest.food;

import org.springframework.beans.factory.annotation.Autowired;
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
                                                 @RequestParam(value = "offset", required=false) Long offset
    ) throws ExecutionException, InterruptedException {
        return foodService.getFilteredFoodModels(foodFilterBody, Optional.ofNullable(offset).orElse(0L));
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
}
