package org.yassir.itlens.service;

import org.yassir.itlens.dto.Survey.SurveyRequest;
import org.yassir.itlens.dto.Survey.SurveyResponse;
import org.yassir.itlens.dto.Survey.SurveyUpdate;

public interface ISurveyService {
    SurveyResponse createSurvey(SurveyRequest surveyRequest);

    SurveyResponse getSurveyById(Long id);

    //    List<CreateSurvey> getAllSurveys();
    SurveyResponse updateSurvey(Long id, SurveyUpdate surveyUpdate);

    void deleteSurvey(Long id);
}