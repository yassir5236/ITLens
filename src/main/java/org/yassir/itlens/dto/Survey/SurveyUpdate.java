package org.yassir.itlens.dto.Survey;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.yassir.itlens.validation.IdExist;
import org.yassir.itlens.validation.UniqueField;

public record SurveyUpdate(

        @NotBlank(message = "title is required")
        @UniqueField
        String title,

        @NotBlank(message = "title is required")
        String description,

        @Column(nullable = false)
        @IdExist(message = "owner with this ID does not exist")
        Long ownerId

) {
}
