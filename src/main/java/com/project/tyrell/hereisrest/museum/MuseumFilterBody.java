package com.project.tyrell.hereisrest.museum;

import com.project.tyrell.hereisrest.shared.RootFilterBody;
import com.project.tyrell.hereisrest.survey.SurveyRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MuseumFilterBody extends RootFilterBody {

    public final MuseumType thematic;

    public MuseumFilterBody(SurveyRequest surveyRequest) {
        super(surveyRequest);
        this.thematic = surveyRequest.thematic;

    }
}
