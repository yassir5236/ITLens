package org.yassir.itlens.service;

import org.yassir.itlens.dto.Participation.ParticipationRequest;
import org.yassir.itlens.dto.Survey.SurveyRequest;
import org.yassir.itlens.dto.Survey.SurveyResponse;
import org.yassir.itlens.dto.Survey.SurveyUpdate;

import java.util.List;

public interface ISurveyService {
    SurveyResponse createSurvey(SurveyRequest surveyRequest);

    SurveyResponse getSurveyById(Long id);

    List<SurveyResponse> getAllSurveys();

    SurveyResponse updateSurvey(Long id, SurveyUpdate surveyUpdate);

    void deleteSurvey(Long id);

  void participateInSurvey(Long surveyId, ParticipationRequest participationRequest) ;

}