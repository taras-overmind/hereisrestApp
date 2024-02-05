package com.project.tyrell.hereisrest.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class SurveyController {

    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("/getSurveyResult")
    public SurveyResponse getSurveyResult(
            @RequestBody SurveyRequest surveyRequest) throws ExecutionException, InterruptedException {
        return surveyService.getSurveyResult(surveyRequest);
    }

}
