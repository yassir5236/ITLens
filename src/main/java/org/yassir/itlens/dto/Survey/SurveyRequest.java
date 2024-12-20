package org.yassir.itlens.dto.Survey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.yassir.itlens.validation.IdExist;
import org.yassir.itlens.validation.UniqueField;

public record SurveyRequest(

        @NotBlank(message = "title required")
        @UniqueField
        String title,

        @NotBlank(message = "description required")
        String description,

        @NotNull(message = "ownerId required")
        @IdExist(message = "owner does not exist  with this ID ")
        Long ownerId

) {
}
