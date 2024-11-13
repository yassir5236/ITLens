package org.yassir.itlens.dto.SurveyEdition;

import org.yassir.itlens.dto.Subject.SubjectResponse;
import org.yassir.itlens.model.Entity.Subject;

import java.time.LocalDate;
import java.util.List;

public record SurveyEditionResponse(

        Long id,
        LocalDate creationDate,
        LocalDate startDate,
        LocalDate endDate,
        int year,
        List<SubjectResponse> subjects

) {
}
