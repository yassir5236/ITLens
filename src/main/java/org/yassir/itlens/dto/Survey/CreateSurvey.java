package org.yassir.itlens.dto.Survey;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSurvey(
        @NotBlank(message = "title required")
        String title,
        @NotBlank(message = "description required")
        String description,
        @NotNull(message = "ownerId required")
//        @JsonProperty("owner_id")
        Long ownerId


) {
}
