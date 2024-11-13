package org.yassir.itlens.dto.SurveyEdition;

import jakarta.persistence.Column;
import org.yassir.itlens.validation.IdExist;
import org.yassir.itlens.validation.UniqueField;

import java.time.LocalDate;

public record SurveyEditionUpdate(
//        @IdExist(message = "")
//        Long id,
        @Column(nullable = false)
        LocalDate creationDate,

        @Column(nullable = false)
        LocalDate startDate,

        @Column(nullable = false)
        LocalDate endDate,
        int year


) {
}
