package org.yassir.itlens.dto.Question;

import org.yassir.itlens.dto.Answer.AnswerResponse;
import org.yassir.itlens.model.Entity.Answer;
import org.yassir.itlens.model.Enum.QuestionType;

import java.util.List;

public record QuestionResponse(
        Long id,
        String text ,
        QuestionType type,
        int answerCount,
        List <AnswerResponse> answers

) {
}
