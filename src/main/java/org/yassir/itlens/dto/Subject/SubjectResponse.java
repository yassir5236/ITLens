package org.yassir.itlens.dto.Subject;

import org.yassir.itlens.dto.Question.QuestionResponse;
import org.yassir.itlens.dto.SubSubject.SubSubjectResponse;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionResponse;
import org.yassir.itlens.model.Entity.Question;
import org.yassir.itlens.model.Entity.SubSubject;
import org.yassir.itlens.model.Entity.SurveyEdition;

import java.util.List;

public record SubjectResponse(
        Long id,
        String title,
        List<SubSubjectResponse> subSubjects
) {
}
