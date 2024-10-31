package org.yassir.itlens.dto.Survey;

import org.yassir.itlens.validation.IdExist;
import org.yassir.itlens.validation.UniqueField;

public record SurveyUpdate(
        @IdExist(message = "survey with this ID does not exist")
        Long id,
        String description,
        @UniqueField
        String title,
        @IdExist(message = "owner with this ID does not exist")
        Long ownerId

) {
}
