package org.yassir.itlens.service;

import org.yassir.itlens.dto.SubSubject.SubSubjectRequest;
import org.yassir.itlens.dto.SubSubject.SubSubjectResponse;
import org.yassir.itlens.dto.SubSubject.SubSubjectUpdate;
import org.yassir.itlens.dto.Subject.SubjectRequest;
import org.yassir.itlens.dto.Subject.SubjectResponse;
import org.yassir.itlens.dto.Subject.SubjectUpdate;

import java.util.List;

public interface ISubSubject {
    SubSubjectResponse createSubSubject(SubSubjectRequest SubSubjectRequest);
    SubSubjectResponse getSubSubjectById(Long id) ;
    SubSubjectResponse updateSubSubject(Long id, SubSubjectUpdate SubSubjectUpdate);
    List<SubSubjectResponse> getAllSubSubjects() ;
    void deleteSubSubject(Long id);
}
