package com.project.tyrell.hereisrest.survey;

import com.project.tyrell.hereisrest.food.FoodModel;
import com.project.tyrell.hereisrest.museum.MuseumModel;
import com.project.tyrell.hereisrest.park.ParkModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SurveyResponse {
    public List<FoodModel> foodModels;
    public List<ParkModel> parkModels;
    public List<MuseumModel> museumModels;

}
