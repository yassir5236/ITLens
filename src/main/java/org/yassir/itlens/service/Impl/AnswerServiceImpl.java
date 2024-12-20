package org.yassir.itlens.service.Impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.itlens.dto.Answer.AnswerRequest;
import org.yassir.itlens.dto.Answer.AnswerResponse;
import org.yassir.itlens.dto.Answer.AnswerResponse;
import org.yassir.itlens.dto.Answer.AnswerUpdate;
import org.yassir.itlens.mapper.Answer.AnswerMapper;
import org.yassir.itlens.mapper.Question.QuestionMapper;
import org.yassir.itlens.model.Entity.Answer;
import org.yassir.itlens.model.Entity.Answer;
import org.yassir.itlens.model.Entity.Question;
import org.yassir.itlens.model.Entity.SubSubject;
import org.yassir.itlens.repository.IAnswerRepository;
import org.yassir.itlens.repository.IQuestionRepository;
import org.yassir.itlens.repository.ISubSubjectRepository;
import org.yassir.itlens.service.IAnswer;
import org.yassir.itlens.service.IQuestion;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements IAnswer {

    private final IAnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final IQuestionRepository questionRepository;

    @Autowired

    public AnswerServiceImpl(IAnswerRepository answerRepository, AnswerMapper answerMapper, IQuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public AnswerResponse createAnswer(AnswerRequest answerRequest) {
        Question question = questionRepository.findById(answerRequest.questionId())
                .orElseThrow(() -> new RuntimeException("subSubject not found with this id "));
        Answer answerSaved = answerRepository.save(answerMapper.toAnswer(answerRequest, question));

//        question.setAnswerCount(question.getAnswerCount() + 1);
//        answerSaved.setSelectionCount(answerSaved.getSelectionCount() + 1);

        questionRepository.save(question);
        return answerMapper.toAnswerResponse(answerSaved);

    }


    @Override
    public AnswerResponse getAnswerById(Long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer nottt found"));
        return answerMapper.toAnswerResponse(answer);
    }


    @Override
    public AnswerResponse updateAnswer(Long answerId, AnswerUpdate answerUpdate) {

        Answer existingAnswer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found"));

        existingAnswer.setText(answerUpdate.text());
        Answer updatedAnswer = answerRepository.save(existingAnswer);

        return answerMapper.toAnswerResponse(updatedAnswer);
    }

    @Override
    public List<AnswerResponse> getAllAnswers() {
        Iterable<Answer> answersIterable = answerRepository.findAll();
        List<AnswerResponse> answersList = new ArrayList<>();

        answersIterable.forEach(answer -> {
            AnswerResponse response = answerMapper.toAnswerResponse(answer);
            answersList.add(response);
        });

        return answersList;
    }


    @Override
    public void deleteAnswer(Long answerId) {
        answerRepository.deleteById(answerId);
    }








    @Transactional
    public void incrementSelectionCount(Long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found with this id"));

        answer.setSelectionCount(answer.getSelectionCount() + 1);
        answerRepository.save(answer);
    }

}

