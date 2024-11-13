package org.yassir.itlens.service;

import org.yassir.itlens.dto.Answer.AnswerRequest;
import org.yassir.itlens.dto.Answer.AnswerResponse;
import org.yassir.itlens.dto.Answer.AnswerUpdate;
import org.yassir.itlens.dto.Question.QuestionRequest;
import org.yassir.itlens.dto.Question.QuestionResponse;

import java.util.List;

public interface IAnswer {
    AnswerResponse createAnswer(AnswerRequest answerRequest);
    AnswerResponse getAnswerById(Long ownerId) ;
    AnswerResponse updateAnswer(Long id, AnswerUpdate answerUpdate);
    List<AnswerResponse> getAllAnswers() ;
    void deleteAnswer(Long ownerId);
}


