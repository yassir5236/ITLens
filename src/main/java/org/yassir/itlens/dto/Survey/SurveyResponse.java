package org.yassir.itlens.dto.Survey;

public record SurveyResponse(
        Long id,
//        Long OwnerId,
        String description,
        String title
) {
}
