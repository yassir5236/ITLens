package org.yassir.itlens.dto.SurveyEdition;

import org.yassir.itlens.validation.IdExist;
import org.yassir.itlens.validation.UniqueField;

import java.time.LocalDate;

public record SurveyEditionUpdate(
        @IdExist(message = "")
        Long id,
        LocalDate creationDate,
        LocalDate startDate,
        LocalDate EndDate,
        int year


) {
}
