package org.yassir.itlens.dto.Answer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.yassir.itlens.validation.IdExist;

public record AnswerUpdate(

        @NotBlank(message = "text is required ")
        String text,

        @IdExist(message = "question with this  Id is not found" )
        Long questionId
) {
}
