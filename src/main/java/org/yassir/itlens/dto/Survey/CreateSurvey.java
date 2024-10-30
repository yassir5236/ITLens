package org.yassir.itlens.dto.Survey;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.yassir.itlens.validation.UniqueField;

public record CreateSurvey(
        @NotBlank(message = "title required")
        @UniqueField
        String title,
        @NotBlank(message = "description required")
        String description,
        @NotNull(message = "ownerId required")
        Long ownerId


) {
}
