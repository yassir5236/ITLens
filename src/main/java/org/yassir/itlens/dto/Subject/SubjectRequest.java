package org.yassir.itlens.dto.Subject;

import jakarta.persistence.Column;
import org.yassir.itlens.validation.IdExist;
import org.yassir.itlens.validation.UniqueField;

public record SubjectRequest(
        @Column(nullable = false)
        @UniqueField
        String title,

        @Column(nullable = false)
        @IdExist(message = "surveyEditionId not found")
        Long surveyEditionId
) {
}
