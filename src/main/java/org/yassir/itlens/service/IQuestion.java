package org.yassir.itlens.service;

import org.yassir.itlens.dto.Question.QuestionRequest;
import org.yassir.itlens.dto.Question.QuestionResponse;
import org.yassir.itlens.dto.Question.QuestionUpdate;

import java.util.List;

public interface IQuestion {
    QuestionResponse createQuestion(QuestionRequest questionRequest);
    QuestionResponse getQuestionById(Long ownerId) ;
    QuestionResponse updateQuestion(Long id, QuestionUpdate ownerUpdate);
    List<QuestionResponse> getAllQuestions() ;
    void deleteQuestion(Long ownerId);
}


