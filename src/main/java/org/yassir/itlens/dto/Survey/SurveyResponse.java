package org.yassir.itlens.dto.Survey;

import org.yassir.itlens.dto.SurveyEdition.SurveyEditionResponse;
import org.yassir.itlens.model.Entity.SurveyEdition;

import java.util.List;

public record SurveyResponse(
        Long id,
        String description,
        String title,
        List<SurveyEditionResponse> surveyEditions
) {
}
