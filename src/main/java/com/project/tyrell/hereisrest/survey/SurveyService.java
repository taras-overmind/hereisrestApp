package com.project.tyrell.hereisrest.survey;

import com.project.tyrell.hereisrest.food.FoodService;
import com.project.tyrell.hereisrest.museum.MuseumFilterBody;
import com.project.tyrell.hereisrest.park.ParkFilterBody;
import com.project.tyrell.hereisrest.museum.MuseumService;
import com.project.tyrell.hereisrest.park.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class SurveyService {
    private final FoodService foodService;
    private final ParkService parkService;
    private final MuseumService museumService;

    @Autowired
    public SurveyService(FoodService foodService, ParkService parkService, MuseumService museumService) {
        this.foodService = foodService;
        this.parkService = parkService;
        this.museumService = museumService;
    }

    public SurveyResponse getSurveyResult(SurveyRequest surveyRequest) throws ExecutionException, InterruptedException {
        SurveyResponse surveyResponse = new SurveyResponse();
        surveyResponse.foodModels = foodService.getFilteredFoodModels(surveyRequest, 0).stream().limit(5).toList();
        surveyResponse.museumModels =
                museumService.getFilteredMuseumPlaces(new MuseumFilterBody(surveyRequest), 0).stream().limit(5).toList();
        surveyResponse.parkModels =
                parkService.getFilteredParkPlaces(new ParkFilterBody(surveyRequest), 0).stream().limit(5).toList();

        return surveyResponse;

    }
}
