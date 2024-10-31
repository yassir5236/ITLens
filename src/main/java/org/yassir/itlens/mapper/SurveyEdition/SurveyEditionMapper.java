package org.yassir.itlens.mapper.SurveyEdition;



import org.mapstruct.Mapper;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionRequest;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionResponse;
import org.yassir.itlens.model.Entity.SurveyEdition;

@Mapper(componentModel = "spring")
public interface SurveyEditionMapper {
    SurveyEdition toEntity(SurveyEditionRequest request);

    SurveyEditionResponse toResponse(SurveyEdition surveyEdition);
}
