package org.yassir.itlens.dto;


import org.yassir.itlens.dto.Answer.AnswerRequest;
import org.yassir.itlens.dto.Question.QuestionRequest;

import java.util.List;

public record QuestionWithAnswersRequest(
        QuestionRequest question,
        List<AnswerRequest> answers
) {
}
