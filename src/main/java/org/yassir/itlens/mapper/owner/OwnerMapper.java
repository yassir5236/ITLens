package org.yassir.itlens.mapper.owner;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.yassir.itlens.dto.Owner.OwnerRequest;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.dto.Owner.OwnerUpdate;
import org.yassir.itlens.dto.Survey.SurveyResponse;
import org.yassir.itlens.model.Entity.Owner;
import org.yassir.itlens.model.Entity.Survey;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    Owner toEntity(OwnerRequest ownerRequest);
    OwnerRequest toOwnerRequest(Owner owner);

    Owner toEntity(OwnerResponse ownerResponse);
    OwnerResponse toOwnerResponse(Owner owner);

    Owner toEntity(OwnerUpdate ownerUpdate);
    OwnerUpdate toOwnerUpdate(Owner owner);


    // Mapper Survey en SurveyResponse
    default SurveyResponse toSurveyResponse(Survey survey) {
        return new SurveyResponse(survey.getId(), survey.getTitle(), survey.getDescription());
    }

    // Conversion de la liste de Survey en liste de SurveyResponse
    default List<SurveyResponse> toSurveyResponses(List<Survey> surveys) {
        return surveys.stream()
                .map(this::toSurveyResponse)
                .toList();
    }

    // Mapper Owner vers OwnerResponse avec les SurveyResponse
    default OwnerResponse toOwnerResponseWithSurveys(Owner owner) {
        return new OwnerResponse(
                owner.getId(),
                owner.getName(),
                toSurveyResponses(owner.getSurveys())
        );
    }

}
