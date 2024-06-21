package com.project.tyrell.hereisrest.root;

import com.project.tyrell.hereisrest.survey.SurveyRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RootFilterBody {
    public boolean parking;
    public boolean finePlaced;
    public boolean animalsAllowed;
    public boolean handicapAccessibility;
    public String city;
    public RootFilterBody(SurveyRequest surveyRequest){
        this.parking=surveyRequest.parking;
        this.finePlaced=surveyRequest.finePlaced;
        this.animalsAllowed=surveyRequest.animalsAllowed;
        this.handicapAccessibility=surveyRequest.handicapAccessibility;
        this.city=surveyRequest.city;

    }
}
