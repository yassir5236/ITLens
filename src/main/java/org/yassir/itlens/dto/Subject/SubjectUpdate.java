package org.yassir.itlens.dto.Subject;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.yassir.itlens.validation.IdExist;
import org.yassir.itlens.validation.UniqueField;

public record SubjectUpdate(


        @NotBlank(message = "title is required")
        @UniqueField
        String title,

        @Column(nullable = false)
        @IdExist(message = "surveyEditionId not found")
        Long surveyEditionId
) {
}
