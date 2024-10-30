package org.yassir.itlens.service;

import org.yassir.itlens.dto.Survey.CreateSurvey;
import org.yassir.itlens.dto.Survey.SurveyRequest;
import org.yassir.itlens.dto.Survey.SurveyResponse;

import java.util.List;

public interface ISurveyService {
    SurveyResponse createSurvey(SurveyRequest surveyRequest);
//    CreateSurvey getSurveyById(Long id);
//    List<CreateSurvey> getAllSurveys();
//    CreateSurvey updateSurvey(Long id, CreateSurvey createSurvey);
    void deleteSurvey(Long id);
}