package com.project.tyrell.hereisrest.park;

import com.project.tyrell.hereisrest.shared.RootFilterBody;
import com.project.tyrell.hereisrest.survey.SurveyRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkFilterBody extends RootFilterBody {
    boolean playground;
    boolean picnic;
    boolean hiking;
    boolean sport;
    boolean cycling;

    public ParkFilterBody(SurveyRequest surveyRequest) {
        super(surveyRequest);
        this.playground = surveyRequest.playground;
        this.picnic = surveyRequest.picnic;
        this.hiking = surveyRequest.hiking;
        this.sport = surveyRequest.sport;
        this.cycling = surveyRequest.cycling;
    }
}
