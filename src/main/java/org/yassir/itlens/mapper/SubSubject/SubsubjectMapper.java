package org.yassir.itlens.mapper.SubSubject;

import org.mapstruct.Mapper;
import org.yassir.itlens.dto.Question.QuestionResponse;
import org.yassir.itlens.dto.SubSubject.SubSubjectRequest;
import org.yassir.itlens.dto.SubSubject.SubSubjectResponse;
import org.yassir.itlens.dto.Subject.SubjectRequest;
import org.yassir.itlens.mapper.Question.QuestionMapper;
import org.yassir.itlens.model.Entity.Question;
import org.yassir.itlens.model.Entity.SubSubject;
import org.yassir.itlens.model.Entity.Subject;
import org.yassir.itlens.model.Entity.SurveyEdition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Mapper(componentModel = "spring")
//
//public interface SubsubjectMapper {
//
//    SubSubject toEntitySubSubject(SubjectRequest subjectRequest);
//
//    default SubSubject toEntity(SubSubjectRequest subSubjectRequest, Subject subject) {
//        SubSubject SubSubjectObj = new SubSubject();
//        SubSubjectObj.setTitle(subSubjectRequest.title());
//        SubSubjectObj.setSubject(subject);
//        return SubSubjectObj;
//    }
//
//    SubSubjectResponse toSubSubjectResponse(SubSubject subSubject);
//
//}

@Mapper(componentModel = "spring", uses = {QuestionMapper.class})
public interface SubsubjectMapper {

    SubSubject toEntitySubSubject(SubjectRequest subjectRequest);

    default SubSubject toEntity(SubSubjectRequest subSubjectRequest, Subject subject) {
        SubSubject subSubjectObj = new SubSubject();
        subSubjectObj.setTitle(subSubjectRequest.title());
        subSubjectObj.setSubject(subject);
        return subSubjectObj;
    }

    SubSubjectResponse toSubSubjectResponse(SubSubject subSubject);


}
