package org.yassir.itlens.dto.SubSubject;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.yassir.itlens.validation.IdExist;
import org.yassir.itlens.validation.UniqueField;

public record SubSubjectRequest(
        @NotBlank(message = "title required")
        @UniqueField
        String title,

        @Column(nullable = false)
        @IdExist(message = "subject Id not found ")
        Long subjectId
) {
}
