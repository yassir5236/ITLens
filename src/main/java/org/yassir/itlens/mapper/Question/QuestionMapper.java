package org.yassir.itlens.mapper.Question;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.yassir.itlens.dto.Answer.AnswerResponse;
import org.yassir.itlens.dto.Question.QuestionRequest;
import org.yassir.itlens.dto.Question.QuestionResponse;
import org.yassir.itlens.mapper.Answer.AnswerMapper;
import org.yassir.itlens.model.Entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {AnswerMapper.class})  // Use AnswerMapper as a dependency
public interface QuestionMapper {

    default Question toQuestion(QuestionRequest questionRequest, SubSubject subSubject) {
        Question question = new Question();
        question.setText(questionRequest.text());
        question.setSubSubject(subSubject);
        question.setAnswerCount(questionRequest.answerCount());
        question.setType(questionRequest.questionType());
        return question;
    }

    default QuestionResponse toQuestionResponse(Question question, AnswerMapper answerMapper) {
        // Use AnswerMapper injected into the method
        List<AnswerResponse> answerResponses = question.getAnswers() != null ?
                question.getAnswers().stream()
                        .map(answer -> answerMapper.toAnswerResponse(answer))  // Inject AnswerMapper here
                        .collect(Collectors.toList()) : new ArrayList<>();

        return new QuestionResponse(
                question.getId(),
                question.getText(),
                question.getAnswerCount(),
                answerResponses
        );
    }
}
