//package org.yassir.itlens.mapper.survey;
//
//import org.mapstruct.Mapper;
//import org.yassir.itlens.dto.Survey.SurveyRequest;
//import org.yassir.itlens.dto.Survey.SurveyResponse;
//import org.yassir.itlens.dto.Survey.SurveyUpdate;
//import org.yassir.itlens.model.Entity.Survey;
//
//@Mapper(componentModel = "spring")
//public interface SurveyMapper {
//    Survey toEntity(SurveyRequest surveyRequest);
//    SurveyRequest toDto(Survey survey);
//
//
//    Survey toEntity(SurveyResponse surveyResponse);
//    SurveyResponse toSurveyResponse(Survey survey);
//
//    Survey toEntity(SurveyUpdate surveyUpdate);
//    SurveyUpdate toSurveyUpdateDto(Survey survey);
//
//}







package org.yassir.itlens.mapper.survey;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.yassir.itlens.dto.Survey.SurveyRequest;
import org.yassir.itlens.dto.Survey.SurveyResponse;
import org.yassir.itlens.dto.Survey.SurveyUpdate;
import org.yassir.itlens.mapper.SurveyEdition.SurveyEditionMapper;
import org.yassir.itlens.model.Entity.Owner;
import org.yassir.itlens.model.Entity.Survey;



@Mapper(componentModel = "spring", uses = SurveyEditionMapper.class) // Utiliser SurveyEditionMapper
public interface SurveyMapper {
    Survey toEntity(SurveyRequest surveyRequest);

    default Survey toEntity(SurveyRequest surveyRequest, Owner owner) {
        Survey survey = new Survey();
        survey.setTitle(surveyRequest.title());
        survey.setDescription(surveyRequest.description());
        survey.setOwner(owner);
        return survey;
    }


    SurveyRequest toDto(Survey survey);

    Survey toEntity(SurveyResponse surveyResponse);

    @Mapping(target = "surveyEditions", source = "surveyEditions") // Le nom doit correspondre à la méthode de votre entité
    SurveyResponse toSurveyResponse(Survey survey);

    Survey toEntity(SurveyUpdate surveyUpdate);
    SurveyUpdate toSurveyUpdateDto(Survey survey);
}
