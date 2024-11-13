package org.yassir.itlens.service;

import org.yassir.itlens.dto.Survey.SurveyRequest;
import org.yassir.itlens.dto.Survey.SurveyResponse;
import org.yassir.itlens.dto.Survey.SurveyUpdate;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionRequest;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionResponse;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionUpdate;

import java.util.List;

public interface ISurveyEdition {
    SurveyEditionResponse createSurveyEdition(SurveyEditionRequest surveyRequest);

    SurveyEditionResponse getSurveyEditionById(Long id);

    List<SurveyEditionResponse> getAllSurveyEditions();

    SurveyEditionResponse updateSurveyEdition(Long id, SurveyEditionUpdate surveyUpdate);

    void deleteSurveyEdition(Long id);
}
