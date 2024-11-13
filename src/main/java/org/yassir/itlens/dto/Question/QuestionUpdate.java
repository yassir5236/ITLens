package org.yassir.itlens.dto.Question;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.yassir.itlens.model.Enum.QuestionType;

public record QuestionUpdate(
        @NotBlank(message = "text requird")
        String text,


        @Column(nullable = false)
        Long subSubjectId,

        @Column(nullable = false)
        QuestionType questionType

) {
}
