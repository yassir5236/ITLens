package org.yassir.itlens.mapper.SurveyEdition;


import org.mapstruct.Mapper;
import org.yassir.itlens.dto.Survey.SurveyRequest;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionRequest;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionResponse;
import org.yassir.itlens.model.Entity.Owner;
import org.yassir.itlens.model.Entity.Survey;
import org.yassir.itlens.model.Entity.SurveyEdition;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SurveyEditionMapper {
    SurveyEdition toEntity(SurveyEditionRequest request);

    default SurveyEdition toEntity(SurveyEditionRequest surveyEditionRequest, Survey survey) {
        SurveyEdition surveyEdition = new SurveyEdition();
        surveyEdition.setYear(surveyEditionRequest.year());
        surveyEdition.setCreationDate(surveyEditionRequest.creationDate());
        surveyEdition.setStartDate(surveyEditionRequest.startDate());
        surveyEdition.setEndDate(surveyEditionRequest.endDate());
        surveyEdition.setSurvey(survey);
        return surveyEdition;
    }


    SurveyEditionResponse toResponse(SurveyEdition surveyEdition);


    default List<SurveyEditionResponse> toResponses(List<SurveyEdition> surveyEditions) {
        return surveyEditions.stream()
                .map(this::toResponse)
                .toList();
    }


}
