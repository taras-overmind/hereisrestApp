package com.project.tyrell.hereisrest.food;

import com.project.tyrell.hereisrest.food.FoodModelClasses.AdditionalService;
import com.project.tyrell.hereisrest.food.FoodModelClasses.FoodCountry;
import com.project.tyrell.hereisrest.food.FoodModelClasses.FoodType;
import com.project.tyrell.hereisrest.food.FoodModelClasses.PlaceType;
import com.project.tyrell.hereisrest.root.RootFilterBody;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class FoodFilterBody extends RootFilterBody {
    List<PlaceType> placeTypes;
    List<FoodCountry> foodCountries;
    List<FoodType> foodTypes;
    List<AdditionalService> services;
    boolean alcohol;
    boolean vip;
}
