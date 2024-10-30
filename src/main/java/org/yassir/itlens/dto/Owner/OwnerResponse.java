package org.yassir.itlens.dto.Owner;

import org.yassir.itlens.dto.Survey.SurveyResponse;
import org.yassir.itlens.model.Entity.Owner;

import java.util.List;
import org.yassir.itlens.model.Entity.Survey;


public record OwnerResponse(
        Long id,
        String name,
        List<SurveyResponse> surveys) {

}

