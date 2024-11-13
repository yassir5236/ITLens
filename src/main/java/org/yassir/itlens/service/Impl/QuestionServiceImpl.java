package org.yassir.itlens.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.itlens.dto.Question.QuestionResponse;
import org.yassir.itlens.dto.Question.QuestionUpdate;
import org.yassir.itlens.dto.Question.QuestionRequest;
import org.yassir.itlens.dto.Question.QuestionResponse;
import org.yassir.itlens.mapper.Answer.AnswerMapper;
import org.yassir.itlens.mapper.Question.QuestionMapper;
import org.yassir.itlens.model.Entity.Question;
import org.yassir.itlens.model.Entity.Question;
import org.yassir.itlens.model.Entity.SubSubject;
import org.yassir.itlens.repository.IQuestionRepository;
import org.yassir.itlens.repository.ISubSubjectRepository;
import org.yassir.itlens.service.IQuestion;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestion {

    private final IQuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final ISubSubjectRepository subSubjectRepository;
    private final AnswerMapper answerMapper;


    @Autowired

    public QuestionServiceImpl(IQuestionRepository questionRepository, QuestionMapper questionMapper, ISubSubjectRepository subSubjectRepository, AnswerMapper answerMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.subSubjectRepository = subSubjectRepository;
        this.answerMapper = answerMapper;
    }

    public QuestionResponse createQuestion(QuestionRequest questionRequest) {
        SubSubject subSubject = subSubjectRepository.findById(questionRequest.subSubjectId())
                .orElseThrow(() -> new RuntimeException("subSubject not found with this id "));
        Question questionSaved = questionRepository.save(questionMapper.toQuestion(questionRequest, subSubject));
        return questionMapper.toQuestionResponse(questionSaved, answerMapper);
    }





    @Override
    public QuestionResponse getQuestionById(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return questionMapper.toQuestionResponse(question, answerMapper);
    }


    @Override
    public QuestionResponse updateQuestion(Long questionId, QuestionUpdate questionUpdate) {
        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        existingQuestion.setText(questionUpdate.text());
        Question updatedQuestion = questionRepository.save(existingQuestion);
        return questionMapper.toQuestionResponse(updatedQuestion, answerMapper);
    }

    @Override
    public List<QuestionResponse> getAllQuestions() {
        Iterable<Question> questionsIterable = questionRepository.findAll();
        List<QuestionResponse> questionsList = new ArrayList<>();
        questionsIterable.forEach(question -> {
            QuestionResponse response = questionMapper.toQuestionResponse(question, answerMapper);
            questionsList.add(response);
        });
        return questionsList;
    }


    @Override
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }




}
