package org.yassir.itlens.mapper.survey;

import org.mapstruct.Mapper;
import org.yassir.itlens.dto.Survey.CreateSurvey;
import org.yassir.itlens.dto.Survey.SurveyRequest;
import org.yassir.itlens.dto.Survey.SurveyResponse;
import org.yassir.itlens.dto.Survey.SurveyUpdate;
import org.yassir.itlens.model.Entity.Survey;

@Mapper(componentModel = "spring")
public interface SurveyMapper {
    Survey toEntity(SurveyRequest surveyRequest);
    SurveyRequest toDto(Survey survey);


    Survey toEntity(SurveyResponse surveyResponse);
    SurveyResponse toSurveyResponse(Survey survey);

    Survey toEntity(SurveyUpdate surveyUpdate);
    SurveyUpdate toSurveyUpdateDto(Survey survey);

}
