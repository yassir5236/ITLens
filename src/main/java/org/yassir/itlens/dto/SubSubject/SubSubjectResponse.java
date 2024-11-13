package org.yassir.itlens.dto.SubSubject;

import org.yassir.itlens.dto.Question.QuestionResponse;

import java.util.List;

public record SubSubjectResponse(
        Long id,
        String title,
        List<QuestionResponse> questions


) {
}
