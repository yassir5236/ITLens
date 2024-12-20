package org.yassir.itlens.mapper.Answer;

import org.mapstruct.Mapper;
import org.yassir.itlens.dto.Answer.AnswerRequest;
import org.yassir.itlens.dto.Answer.AnswerResponse;
import org.yassir.itlens.dto.Question.QuestionRequest;
import org.yassir.itlens.dto.Question.QuestionResponse;
import org.yassir.itlens.model.Entity.Answer;
import org.yassir.itlens.model.Entity.Question;
import org.yassir.itlens.model.Entity.SubSubject;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer toAnswer(AnswerRequest answerRequest, Question question) {
        Answer answer = new Answer();
        answer.setText(answerRequest.text());
//        answer.setSelectionCount(answerRequest.selectionCount());
        answer.setQuestion(question);
        return answer;
    }

    default AnswerResponse toAnswerResponse(Answer answer) {
        return new AnswerResponse(
                answer.getId(),
                answer.getText(),
                answer.getSelectionCount()
//                answer.getQuestion() != null ? answer.getQuestion().getId() : null
        );
    }

}
