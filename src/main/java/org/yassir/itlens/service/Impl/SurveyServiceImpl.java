package org.yassir.itlens.service.Impl;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.dto.Owner.OwnerUpdate;
import org.yassir.itlens.dto.Participation.AnswerPayload;
import org.yassir.itlens.dto.Participation.ParticipationRequest;
import org.yassir.itlens.dto.Participation.Response;
import org.yassir.itlens.dto.Survey.SurveyRequest;
import org.yassir.itlens.dto.Survey.SurveyResponse;
import org.yassir.itlens.dto.Survey.SurveyUpdate;
import org.yassir.itlens.mapper.survey.SurveyMapper;
import org.yassir.itlens.model.Entity.Answer;
import org.yassir.itlens.model.Entity.Owner;
import org.yassir.itlens.model.Entity.Question;
import org.yassir.itlens.model.Entity.Survey;
import org.yassir.itlens.model.Enum.QuestionType;
import org.yassir.itlens.repository.IAnswerRepository;
import org.yassir.itlens.repository.IQuestionRepository;
import org.yassir.itlens.repository.ISurveyRepository;
import org.yassir.itlens.repository.OwnerRepository;
import org.yassir.itlens.service.ISurveyService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyServiceImpl implements ISurveyService {

    private final ISurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;
    private final OwnerRepository ownerRepository;
    private final IAnswerRepository answerRepository;
    private final IQuestionRepository questionRepository;



    @Autowired
    public SurveyServiceImpl(ISurveyRepository surveyRepository, SurveyMapper surveyMapper, OwnerRepository ownerRepository,
                             IAnswerRepository answerRepository, IQuestionRepository questionRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;
        this.ownerRepository = ownerRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }


    @Override
    public SurveyResponse createSurvey(SurveyRequest surveyRequest) {
        Owner owner = ownerRepository.findById(surveyRequest.ownerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found"));

        Survey survey = surveyMapper.toEntity(surveyRequest, owner);

        Survey savedSurvey = surveyRepository.save(survey);

        return surveyMapper.toSurveyResponse(savedSurvey);
    }


    @Override
    public SurveyResponse getSurveyById(Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found with id " + id));
        return surveyMapper.toSurveyResponse(survey);
    }

    @Override
    public List<SurveyResponse> getAllSurveys() {
        Iterable<Survey> surveys = surveyRepository.findAll();
        List<SurveyResponse> surveyResponses = new ArrayList<>();
        surveys.forEach(survey -> surveyResponses.add(surveyMapper.toSurveyResponse(survey)));
        return surveyResponses;
    }


    @Override
    public SurveyResponse updateSurvey(Long id, SurveyUpdate surveyUpdate) {
        Survey existingSurvey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found with id " + id));
        Owner owner = ownerRepository.findById(surveyUpdate.ownerId())
                .orElseThrow(() -> new RuntimeException("Owner not found with id " + surveyUpdate.ownerId()));


        existingSurvey.setDescription(surveyUpdate.description());
        existingSurvey.setTitle(surveyUpdate.title());
        existingSurvey.setOwner(owner);

        Survey updatedSurvey = surveyRepository.save(existingSurvey);
        return surveyMapper.toSurveyResponse(updatedSurvey);
    }


    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }


//    @Transactional
//    @Override
//    public void participateInSurvey(Long surveyId, ParticipationRequest participationRequest) {
//        for (Response response : participationRequest.responses()) {
//            Question question = questionRepository.findById(response.questionId())
//                    .orElseThrow(() -> new EntityNotFoundException("Question not found with id " + response.questionId()));
//
//            for (AnswerPayload answerPayload : response.answers()) {
//                Answer answer = answerRepository.findById(answerPayload.answerId())
//                        .orElseThrow(() -> new EntityNotFoundException("Answer not found with id " + answerPayload.answerId()));
//
////                question.setAnswerCount(question.getAnswerCount() + 1);
////                questionRepository.save(question);
//
//
//
//                answer.setSelectionCount(answer.getSelectionCount() + 1);
//                answerRepository.save(answer);
//            }
//        }
//    }


    @Transactional
    @Override
    public void participateInSurvey(Long surveyId, ParticipationRequest participationRequest) {
        for (Response response : participationRequest.responses()) {
            Question question = questionRepository.findById(response.questionId())
                    .orElseThrow(() -> new EntityNotFoundException("Question not found with id " + response.questionId()));

            if (question.getType() == QuestionType.CHOIX_UNIQUE) {
                if (response.answers().size() != 1) {
                    throw new IllegalArgumentException("Only one answer is allowed for a single-choice question");
                }

                AnswerPayload singleAnswerPayload = response.answers().get(0);

                Answer answer = answerRepository.findById(singleAnswerPayload.answerId())
                        .orElseThrow(() -> new EntityNotFoundException("Answer not found with id " + singleAnswerPayload.answerId()));

                answer.setSelectionCount(answer.getSelectionCount() + 1);
                question.setAnswerCount(question.getAnswerCount() + 1);

                answerRepository.save(answer);

            } else {
                for (AnswerPayload answerPayload : response.answers()) {
                    Answer answer = answerRepository.findById(answerPayload.answerId())
                            .orElseThrow(() -> new EntityNotFoundException("Answer not found with id " + answerPayload.answerId()));

                    answer.setSelectionCount(answer.getSelectionCount() + 1);
                    answerRepository.save(answer);
                }
            }
        }
    }




}

