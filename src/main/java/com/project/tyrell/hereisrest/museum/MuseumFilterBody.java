package com.project.tyrell.hereisrest.museum;

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
public class MuseumFilterBody extends RootFilterBody {

    MuseumType thematic;

    public MuseumFilterBody(SurveyRequest surveyRequest) {
        super(surveyRequest);
        this.thematic = surveyRequest.thematic;

    }
}
