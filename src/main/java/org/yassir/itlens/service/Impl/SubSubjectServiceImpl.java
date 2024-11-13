package org.yassir.itlens.service.Impl;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.itlens.dto.SubSubject.SubSubjectRequest;
import org.yassir.itlens.dto.SubSubject.SubSubjectResponse;
import org.yassir.itlens.dto.SubSubject.SubSubjectUpdate;
import org.yassir.itlens.mapper.SubSubject.SubsubjectMapper;
import org.yassir.itlens.model.Entity.SubSubject;
import org.yassir.itlens.model.Entity.Subject;
import org.yassir.itlens.repository.ISubSubjectRepository;

import org.yassir.itlens.repository.ISubjectRepository;
import org.yassir.itlens.service.ISubSubject;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubSubjectServiceImpl implements ISubSubject {

    private final ISubSubjectRepository subSubjectRepository;
    private final SubsubjectMapper subSubjectMapper;
    private final ISubjectRepository subjectRepository;

    @Autowired
    public SubSubjectServiceImpl(ISubSubjectRepository subSubjectRepository, SubsubjectMapper subSubjectMapper, ISubjectRepository subjectRepository) {
        this.subSubjectRepository = subSubjectRepository;
        this.subSubjectMapper = subSubjectMapper;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public SubSubjectResponse createSubSubject(SubSubjectRequest subSubjectRequest) {
        Subject subject = subjectRepository.findById(subSubjectRequest.subjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id "));

        SubSubject subSubject = subSubjectMapper.toEntity(subSubjectRequest, subject);
        SubSubject savedSubSubject = subSubjectRepository.save(subSubject);

        return subSubjectMapper.toSubSubjectResponse(savedSubSubject);
    }

    @Override
    public SubSubjectResponse getSubSubjectById(Long id) {
        SubSubject subSubject = subSubjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SubSubject not found with id " + id));
        return subSubjectMapper.toSubSubjectResponse(subSubject);
    }

    @Override
    public SubSubjectResponse updateSubSubject(Long id, SubSubjectUpdate subSubjectUpdate) {
        SubSubject existingSubSubject = subSubjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SubSubject not found with id " + id));

        Subject subject = subjectRepository.findById(subSubjectUpdate.subjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id " ));

        existingSubSubject.setTitle(subSubjectUpdate.title());
        existingSubSubject.setSubject(subject);

        SubSubject updatedSubSubject = subSubjectRepository.save(existingSubSubject);
        return subSubjectMapper.toSubSubjectResponse(updatedSubSubject);
    }

    @Override
    public List<SubSubjectResponse> getAllSubSubjects() {
        List<SubSubject> subSubjects = (List<SubSubject>) subSubjectRepository.findAll();
        return subSubjects.stream()
                .map(subSubjectMapper::toSubSubjectResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubSubject(Long id) {
        if (!subSubjectRepository.existsById(id)) {
            throw new EntityNotFoundException("SubSubject not found with id " + id);
        }
        subSubjectRepository.deleteById(id);
    }
}
