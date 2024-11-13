package org.yassir.itlens.service;

import org.yassir.itlens.dto.Owner.OwnerRequest;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.dto.Owner.OwnerUpdate;
import org.yassir.itlens.dto.Subject.SubjectRequest;
import org.yassir.itlens.dto.Subject.SubjectResponse;
import org.yassir.itlens.dto.Subject.SubjectUpdate;

import java.util.List;

public interface ISubject {
    SubjectResponse createSubject(SubjectRequest SubjectRequest);
    SubjectResponse getSubjectById(Long id) ;
    SubjectResponse updateSubject(Long id, SubjectUpdate SubjectUpdate);
    List<SubjectResponse> getAllSubjects() ;
    void deleteSubject(Long id);
}
