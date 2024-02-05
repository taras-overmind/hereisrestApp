package com.project.tyrell.hereisrest.survey;

import com.project.tyrell.hereisrest.food.FoodFilterBody;
import com.project.tyrell.hereisrest.museum.MuseumType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SurveyRequest extends FoodFilterBody {

    public boolean playground;
    public boolean picnic;
    public boolean hiking;
    public boolean sport;
    public boolean cycling;
    public MuseumType thematic;
}
