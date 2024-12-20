package org.yassir.itlens.dto.Answer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.yassir.itlens.model.Enum.QuestionType;
import org.yassir.itlens.validation.IdExist;

public record AnswerRequest(
        @NotBlank(message = "text is required")
        String text,

        @Column(nullable = false)
        @IdExist(message = "questionId not found ")
        Long questionId
) {
}
