package org.yassir.itlens.dto.SurveyEdition;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record SurveyEditionRequest(


        @Column(nullable = false)
        LocalDate creationDate,

        @Column(nullable = false)
        LocalDate startDate,

        @Column(nullable = false)
        LocalDate endDate,

        @Column(nullable = false)
        int year,

        @Column(nullable = false)
        Long surveyId


) {
}
