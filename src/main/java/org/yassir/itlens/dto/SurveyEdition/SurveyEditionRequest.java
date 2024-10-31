package org.yassir.itlens.dto.SurveyEdition;

import java.time.LocalDate;

public record SurveyEditionRequest(


        LocalDate creationDate,
        LocalDate startDate,
        LocalDate endDate,
        int year,
        Long surveyId


) {
}
