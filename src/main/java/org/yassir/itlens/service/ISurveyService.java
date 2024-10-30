package org.yassir.itlens.service;

import org.yassir.itlens.dto.Survey.CreateSurvey;

import java.util.List;

public interface ISurveyService {
    CreateSurvey createSurvey(CreateSurvey createSurvey);
    CreateSurvey getSurveyById(Long id);
    List<CreateSurvey> getAllSurveys();
//    CreateSurvey updateSurvey(Long id, CreateSurvey createSurvey);
    void deleteSurvey(Long id);
}