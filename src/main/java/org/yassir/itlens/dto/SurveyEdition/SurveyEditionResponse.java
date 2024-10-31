package org.yassir.itlens.dto.SurveyEdition;

import java.time.LocalDate;

public record SurveyEditionResponse(
        Long id,
        LocalDate creationDate,
        LocalDate startDate,
        LocalDate endDate,
        int year

) {
}
