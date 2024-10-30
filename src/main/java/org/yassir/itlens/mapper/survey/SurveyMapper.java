package org.yassir.itlens.mapper.survey;

import org.mapstruct.Mapper;
import org.yassir.itlens.dto.Survey.CreateSurvey;
import org.yassir.itlens.model.Entity.Survey;

@Mapper(componentModel = "spring")
public interface SurveyMapper {
    Survey toEntity(CreateSurvey createSurvey);
    CreateSurvey toDto(Survey survey);
}
