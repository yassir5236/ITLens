package org.yassir.itlens.mapper.owner;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.yassir.itlens.dto.Owner.OwnerRequest;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.dto.Owner.OwnerUpdate;
import org.yassir.itlens.dto.Survey.SurveyResponse;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionResponse;
import org.yassir.itlens.mapper.SurveyEdition.SurveyEditionMapper;
import org.yassir.itlens.model.Entity.Owner;
import org.yassir.itlens.model.Entity.Survey;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    SurveyEditionMapper surveyEditionMapper = Mappers.getMapper(SurveyEditionMapper.class); // Ajoutez cette ligne


    Owner toEntity(OwnerRequest ownerRequest);
    OwnerRequest toOwnerRequest(Owner owner);

    Owner toEntity(OwnerResponse ownerResponse);
    OwnerResponse toOwnerResponse(Owner owner);

    Owner toEntity(OwnerUpdate ownerUpdate);
    OwnerUpdate toOwnerUpdate(Owner owner);


    default SurveyResponse toSurveyResponse(Survey survey) {
        List<SurveyEditionResponse> surveyEditionResponses = survey.getSurveyEditions().stream()
                .map(surveyEditionMapper::toResponse)
                .toList();

        return new SurveyResponse(survey.getId(), survey.getDescription(), survey.getTitle(), surveyEditionResponses);
    }

    default List<SurveyResponse> toSurveyResponses(List<Survey> surveys) {
        return surveys.stream()
                .map(this::toSurveyResponse)
                .toList();
    }

    default OwnerResponse toOwnerResponseWithSurveys(Owner owner) {
        return new OwnerResponse(
                owner.getId(),
                owner.getName(),
                toSurveyResponses(owner.getSurveys())
        );
    }

}
