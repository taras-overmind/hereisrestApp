package com.project.tyrell.hereisrest.food;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.project.tyrell.hereisrest.food.FoodModelClasses.AdditionalService;
import com.project.tyrell.hereisrest.food.FoodModelClasses.FoodCountry;
import com.project.tyrell.hereisrest.food.FoodModelClasses.FoodType;
import com.project.tyrell.hereisrest.food.FoodModelClasses.PlaceType;
import com.project.tyrell.hereisrest.shared.RootModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public final class FoodModel extends RootModel {

    String menu; //optional

    PlaceType placeType;

    List<FoodCountry> foodCountries = new ArrayList<>();

    List<FoodType> foodTypes = new ArrayList<>();

    List<AdditionalService> services = new ArrayList<>();

    boolean alcohol;

    boolean vip;

    public boolean matchesFilter(FoodModel foodModelEntity, FoodFilterBody filter) {
        return super.matchesFilter(foodModelEntity, filter)
                && (!filter.alcohol || foodModelEntity.alcohol)
                && (!filter.vip || foodModelEntity.vip);
    }

    public int getRelevancyRating(FoodModel foodModel, FoodFilterBody foodFilterBody) {
        int rating = 0;
        if (foodFilterBody.placeTypes != null) {
            if ((foodModel.placeType != null) && foodFilterBody.placeTypes.contains(foodModel.placeType)) {
                rating += 30;
            } else {
                rating -= 30;
            }
        }

        if (foodFilterBody.foodCountries != null) {
            if (foodModel.foodCountries != null) {
                Set<FoodCountry> countriesIntersection = new HashSet<>(foodFilterBody.foodCountries);
                countriesIntersection.retainAll(foodModel.foodCountries);
                if (!countriesIntersection.isEmpty()) {
                    rating += 20;
                    if (countriesIntersection.size() > 1) {
                        rating += 5 * (countriesIntersection.size() - 1);
                    }
                } else {
                    rating -= 20;
                }
            } else {
                rating -= 20;
            }
        }

        if (foodFilterBody.foodTypes != null) {
            if (foodModel.foodTypes != null) {
                Set<FoodType> foodTypesIntersection = new HashSet<>(foodFilterBody.foodTypes);
                foodTypesIntersection.retainAll(foodModel.foodTypes);
                if (!foodTypesIntersection.isEmpty()) {
                    rating += 10;
                    if (foodTypesIntersection.size() > 1) {
                        rating += 5 * (foodTypesIntersection.size() - 1);
                    }
                } else {
                    rating -= 20;
                }
            } else {
                rating -= 20;
            }
        }


        if (foodFilterBody.services != null) {
            if (foodModel.services != null) {
                Set<AdditionalService> servicesIntersection = new HashSet<>(foodFilterBody.services);
                servicesIntersection.retainAll(foodModel.services);
                if (!servicesIntersection.isEmpty()) {
                    rating += 10;
                    if (servicesIntersection.size() > 1) {
                        rating += 5 * (servicesIntersection.size() - 1);
                    }
                } else {
                    rating -= 20;
                }
            } else {
                rating -= 20;
            }
        }

        rating += foodModel.subscriptionPlan.ordinal() * 20;
        //or rating = (int) (rating*foodModel.getSubscription_plan()); if subscription plans will work like multiplier
        return rating;
    }
}
