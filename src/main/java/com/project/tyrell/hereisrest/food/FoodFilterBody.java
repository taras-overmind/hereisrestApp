package com.project.tyrell.hereisrest.food;

import com.project.tyrell.hereisrest.food.FoodModelClasses.AdditionalService;
import com.project.tyrell.hereisrest.food.FoodModelClasses.FoodCountry;
import com.project.tyrell.hereisrest.food.FoodModelClasses.FoodType;
import com.project.tyrell.hereisrest.food.FoodModelClasses.PlaceType;
import com.project.tyrell.hereisrest.shared.RootFilterBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodFilterBody extends RootFilterBody {
    List<PlaceType> placeTypes;
    List<FoodCountry> foodCountries;
    List<FoodType> foodTypes;
    List<AdditionalService> services;
    boolean alcohol;
    boolean vip;
}
