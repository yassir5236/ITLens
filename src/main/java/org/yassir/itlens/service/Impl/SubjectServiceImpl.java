////package org.yassir.itlens.service.Impl;
////
////import jakarta.persistence.EntityNotFoundException;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////import org.yassir.itlens.dto.Subject.SubjectRequest;
////import org.yassir.itlens.dto.Subject.SubjectResponse;
////import org.yassir.itlens.dto.Subject.SubjectUpdate;
////import org.yassir.itlens.dto.Survey.SurveyRequest;
////import org.yassir.itlens.dto.Survey.SurveyResponse;
////import org.yassir.itlens.mapper.Subject.SubjectMapper;
////import org.yassir.itlens.model.Entity.Owner;
////import org.yassir.itlens.model.Entity.Subject;
////import org.yassir.itlens.model.Entity.Survey;
////import org.yassir.itlens.model.Entity.SurveyEdition;
////import org.yassir.itlens.repository.ISubjectRepository;
////import org.yassir.itlens.repository.ISurveyEditionRepository;
////import org.yassir.itlens.service.ISubject;
////
////import java.util.List;
////@Service
////
////public class SubjectServiceImpl implements ISubject {
////
////    private final ISubjectRepository subjectRepository;
////    private final SubjectMapper subjectMapper;
////    private final ISurveyEditionRepository surveyEditionRepository;
////
////    @Autowired
////    public SubjectServiceImpl(ISubjectRepository subjectRepository, SubjectMapper subjectMapper, ISurveyEditionRepository iSurveyEditionRepository) {
////        this.subjectRepository = subjectRepository;
////        this.subjectMapper = subjectMapper;
////        this.surveyEditionRepository = iSurveyEditionRepository;
////    }
////
////
////    public SubjectResponse createSubject(SubjectRequest subjectRequest) {
////
////        SurveyEdition surveyEdition = surveyEditionRepository.findById(subjectRequest.surveyEditionId())
////                .orElseThrow(() -> new EntityNotFoundException("Survey Edition Not Found"));
////
////         Subject subject = subjectMapper.toEntity(subjectRequest,surveyEdition);
////
////         subjectRepository.save(subject);
////        return subjectMapper.toSubjectResponse(subject);
////
////
////    }
////
////
////
////
////
//////    public SubjectResponse getSubjectById(Long id){
//////
//////    }
//////    public SubjectResponse updateSubject(Long id, SubjectUpdate SubjectUpdate);
//////    public List<SubjectResponse> getAllSubjects() ;
//////    public void deleteSubject(Long id);
////
////
////}
//
//
//
//
//package org.yassir.itlens.service.Impl;
//
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.yassir.itlens.dto.Subject.SubjectRequest;
//import org.yassir.itlens.dto.Subject.SubjectResponse;
//import org.yassir.itlens.dto.Subject.SubjectUpdate;
//import org.yassir.itlens.mapper.Subject.SubjectMapper;
//import org.yassir.itlens.model.Entity.Subject;
//import org.yassir.itlens.model.Entity.SurveyEdition;
//import org.yassir.itlens.repository.ISubjectRepository;
//import org.yassir.itlens.repository.ISurveyEditionRepository;
//import org.yassir.itlens.service.ISubject;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class SubjectServiceImpl implements ISubject {
//
//    private final ISubjectRepository subjectRepository;
//    private final SubjectMapper subjectMapper;
//    private final ISurveyEditionRepository surveyEditionRepository;
//
//    @Autowired
//    public SubjectServiceImpl(ISubjectRepository subjectRepository, SubjectMapper subjectMapper, ISurveyEditionRepository surveyEditionRepository) {
//        this.subjectRepository = subjectRepository;
//        this.subjectMapper = subjectMapper;
//        this.surveyEditionRepository = surveyEditionRepository;
//    }
//
//    @Override
//    public SubjectResponse createSubject(SubjectRequest subjectRequest) {
//        SurveyEdition surveyEdition = surveyEditionRepository.findById(subjectRequest.surveyEditionId())
//                .orElseThrow(() -> new EntityNotFoundException("Survey Edition Not Found"));
//
//        Subject subject = subjectMapper.toEntity(subjectRequest, surveyEdition);
//        Subject savedSubject = subjectRepository.save(subject);
//
//        return subjectMapper.toSubjectResponse(savedSubject);
//    }
//
//    @Override
//    public SubjectResponse getSubjectById(Long id) {
//        Subject subject = subjectRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id " + id));
//        return subjectMapper.toSubjectResponse(subject);
//    }
//
//    @Override
//    public SubjectResponse updateSubject(Long id, SubjectUpdate subjectUpdate) {
//        Subject existingSubject = subjectRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id " + id));
//
//        SurveyEdition surveyEdition = surveyEditionRepository.findById(subjectUpdate.surveyEditionId())
//                .orElseThrow(() -> new EntityNotFoundException("Survey Edition not found with id " + subjectUpdate.surveyEditionId()));
//
//        existingSubject.setTitle(subjectUpdate.title());
//        existingSubject.setSurveyEdition(surveyEdition);
//
//        Subject updatedSubject = subjectRepository.save(existingSubject);
//        return subjectMapper.toSubjectResponse(updatedSubject);
//    }
//
//    @Override
//    public List<SubjectResponse> getAllSubjects() {
//        List<Subject> subjects = (List<Subject>) subjectRepository.findAll();
//        return subjects.stream()
//                .map(subjectMapper::toSubjectResponse)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void deleteSubject(Long id) {
//        if (!subjectRepository.existsById(id)) {
//            throw new EntityNotFoundException("Subject not found with id " + id);
//        }
//        subjectRepository.deleteById(id);
//    }
//}














package org.yassir.itlens.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.itlens.dto.Subject.SubjectRequest;
import org.yassir.itlens.dto.Subject.SubjectResponse;
import org.yassir.itlens.dto.Subject.SubjectUpdate;
import org.yassir.itlens.mapper.Subject.SubjectMapper;
import org.yassir.itlens.mapper.SubSubject.SubsubjectMapper;  // Add this import
import org.yassir.itlens.model.Entity.Subject;
import org.yassir.itlens.model.Entity.SurveyEdition;
import org.yassir.itlens.repository.ISubjectRepository;
import org.yassir.itlens.repository.ISurveyEditionRepository;
import org.yassir.itlens.service.ISubject;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements ISubject {

    private final ISubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final ISurveyEditionRepository surveyEditionRepository;
    private final SubsubjectMapper subsubjectMapper;  // Inject SubsubjectMapper

    @Autowired
    public SubjectServiceImpl(ISubjectRepository subjectRepository,
                              SubjectMapper subjectMapper,
                              ISurveyEditionRepository surveyEditionRepository,
                              SubsubjectMapper subsubjectMapper) {  // Inject SubsubjectMapper here
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
        this.surveyEditionRepository = surveyEditionRepository;
        this.subsubjectMapper = subsubjectMapper;  // Assign the injected mapper
    }

    @Override
    public SubjectResponse createSubject(SubjectRequest subjectRequest) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(subjectRequest.surveyEditionId())
                .orElseThrow(() -> new EntityNotFoundException("Survey Edition Not Found"));

        Subject subject = subjectMapper.toEntity(subjectRequest, surveyEdition);
        Subject savedSubject = subjectRepository.save(subject);

        // Pass subsubjectMapper to the toSubjectResponse method
        return subjectMapper.toSubjectResponse(savedSubject, subsubjectMapper);
    }

    @Override
    public SubjectResponse getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id " + id));

        // Pass subsubjectMapper to the toSubjectResponse method
        return subjectMapper.toSubjectResponse(subject, subsubjectMapper);
    }

    @Override
    public SubjectResponse updateSubject(Long id, SubjectUpdate subjectUpdate) {
        Subject existingSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id " + id));

        SurveyEdition surveyEdition = surveyEditionRepository.findById(subjectUpdate.surveyEditionId())
                .orElseThrow(() -> new EntityNotFoundException("Survey Edition not found with id " + subjectUpdate.surveyEditionId()));

        existingSubject.setTitle(subjectUpdate.title());
        existingSubject.setSurveyEdition(surveyEdition);

        Subject updatedSubject = subjectRepository.save(existingSubject);

        // Pass subsubjectMapper to the toSubjectResponse method
        return subjectMapper.toSubjectResponse(updatedSubject, subsubjectMapper);
    }

    @Override
    public List<SubjectResponse> getAllSubjects() {
        List<Subject> subjects = (List<Subject>) subjectRepository.findAll();
        return subjects.stream()
                .map(subject -> subjectMapper.toSubjectResponse(subject, subsubjectMapper)) // Pass subsubjectMapper here
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new EntityNotFoundException("Subject not found with id " + id);
        }
        subjectRepository.deleteById(id);
    }
}
