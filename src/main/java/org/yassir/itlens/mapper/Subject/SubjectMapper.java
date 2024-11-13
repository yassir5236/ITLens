//package org.yassir.itlens.mapper.Subject;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.yassir.itlens.dto.Answer.AnswerResponse;
//import org.yassir.itlens.dto.Owner.OwnerResponse;
//import org.yassir.itlens.dto.Question.QuestionResponse;
//import org.yassir.itlens.dto.SubSubject.SubSubjectResponse;
//import org.yassir.itlens.dto.Subject.SubjectRequest;
//import org.yassir.itlens.dto.Subject.SubjectResponse;
//import org.yassir.itlens.dto.Subject.SubjectUpdate;
//import org.yassir.itlens.dto.Survey.SurveyRequest;
//import org.yassir.itlens.dto.SurveyEdition.SurveyEditionResponse;
//import org.yassir.itlens.mapper.Question.QuestionMapper;
//import org.yassir.itlens.mapper.SubSubject.SubsubjectMapper;
//import org.yassir.itlens.model.Entity.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Mapper(componentModel = "spring", uses = {SubsubjectMapper.class, QuestionMapper.class})
//// Injecting the other mappers here
//public interface SubjectMapper {
//    SubjectRequest toSubjectRequest(Subject subject);
//
//    default Subject toEntity(SubjectRequest subjectRequest, SurveyEdition surveyEdition) {
//        Subject subjectObj = new Subject();
//        subjectObj.setTitle(subjectRequest.title());
//        subjectObj.setSurveyEdition(surveyEdition);
//        return subjectObj;
//    }
//
//
//
//    default SubjectResponse toSubjectResponse(Subject subject){
//        return new SubjectResponse(
//                subject.getId(),
//                subject.getTitle(),
//                subject.getSubSubjects() != null ? subject.getSubSubjects() : null
//        );
//    }
//
//
//
//
//    SubjectRequest toEntityResponse(SubjectRequest subjectRequest);
//
//
//}








package org.yassir.itlens.mapper.Subject;

import org.mapstruct.Mapper;
import org.yassir.itlens.dto.SubSubject.SubSubjectResponse;
import org.yassir.itlens.dto.Subject.SubjectRequest;
import org.yassir.itlens.dto.Subject.SubjectResponse;
import org.yassir.itlens.mapper.SubSubject.SubsubjectMapper; // Ensure this import is correct
import org.yassir.itlens.model.Entity.Subject;
import org.yassir.itlens.model.Entity.SurveyEdition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {SubsubjectMapper.class})
public interface SubjectMapper {

    SubjectRequest toSubjectRequest(Subject subject);

    default Subject toEntity(SubjectRequest subjectRequest, SurveyEdition surveyEdition) {
        Subject subjectObj = new Subject();
        subjectObj.setTitle(subjectRequest.title());
        subjectObj.setSurveyEdition(surveyEdition);
        return subjectObj;
    }

    // Pass subsubjectMapper as a parameter here
    default SubjectResponse toSubjectResponse(Subject subject, SubsubjectMapper subsubjectMapper) {
//        List<SubSubjectResponse> subSubjectResponses = subject.getSubSubjects() != null
//                ? subject.getSubSubjects().stream()
//                .map(subSubject -> subsubjectMapper.toSubSubjectResponse(subSubject))  // Use injected subsubjectMapper
//                .collect(Collectors.toList())
//                : new ArrayList<>();

        List<SubSubjectResponse> subSubjectResponses = (subject.getSubSubjects() != null && !subject.getSubSubjects().isEmpty())
                ? subject.getSubSubjects().stream()
                .map(subSubject -> subsubjectMapper.toSubSubjectResponse(subSubject))
                .collect(Collectors.toList())
                : null;

        return new SubjectResponse(
                subject.getId(),
                subject.getTitle(),
                subSubjectResponses
        );
    }

    SubjectRequest toEntityResponse(SubjectRequest subjectRequest);
}

