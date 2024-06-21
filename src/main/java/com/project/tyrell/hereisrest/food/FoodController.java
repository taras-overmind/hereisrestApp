package com.project.tyrell.hereisrest.food;

import com.project.tyrell.hereisrest.shared.ModelDistanceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/food")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("")
    public List<FoodModel> getAll() throws ExecutionException, InterruptedException {
        return foodService.getFoodModelEntities();
    }

    @GetMapping("/sorted/{page}")
    public List<FoodModel> getSortedAndPaginated(FoodFilterBody foodFilterBody,
                                                 @PathVariable("page") Long offset
    ) throws ExecutionException, InterruptedException {
        return foodService.getFilteredFoodModels(foodFilterBody, offset);
    }

    @GetMapping("/distance")
    public List<ModelDistanceBean> getWithDistance(FoodFilterBody foodFilterBody,
                                                         @RequestParam(value = "latitude") Double latitude,
                                                         @RequestParam(value = "longitude") Double longitude
    ) throws ExecutionException, InterruptedException {
        return foodService.getFilteredFoodModelsWithDistance(foodFilterBody, 0L, latitude, longitude);
    }

    @GetMapping("/id/{id}")
    public FoodModel getFoodModelById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return foodService.getFoodModelById(id);
    }

    @GetMapping("/name/{name}")
    public FoodModel getFoodModelByName(@PathVariable("name") String name) throws ExecutionException,
            InterruptedException {
        return foodService.getFoodModelByName(name);
    }
}
