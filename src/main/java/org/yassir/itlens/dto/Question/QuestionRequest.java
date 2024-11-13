package org.yassir.itlens.dto.Question;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.yassir.itlens.model.Entity.Answer;
import org.yassir.itlens.model.Enum.QuestionType;

public record QuestionRequest(
        @NotBlank(message = "text requird")
        String text,

        @Column(nullable = false)
        int answerCount,


        @Column(nullable = false)
        Long subSubjectId,

        @Column(nullable = false)
        QuestionType questionType


) {
}
