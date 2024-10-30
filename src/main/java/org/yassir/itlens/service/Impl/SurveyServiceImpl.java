package org.yassir.itlens.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.itlens.dto.Survey.CreateSurvey;
import org.yassir.itlens.mapper.survey.SurveyMapper;
import org.yassir.itlens.model.Entity.Owner;
import org.yassir.itlens.model.Entity.Survey;
import org.yassir.itlens.repository.ISurveyRepository;
import org.yassir.itlens.repository.OwnerRepository;
import org.yassir.itlens.service.ISurveyService;

import java.util.stream.Collectors;

import java.util.List;

@Service
public class SurveyServiceImpl implements ISurveyService {

    private final ISurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;
    private final OwnerRepository ownerRepository;

    @Autowired
    public SurveyServiceImpl(ISurveyRepository surveyRepository, SurveyMapper surveyMapper , OwnerRepository ownerRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;
        this.ownerRepository = ownerRepository;
    }

//    @Override
//    public CreateSurvey createSurvey(CreateSurvey createSurvey) {
//        System.out.println(createSurvey.ownerId());
//        return surveyMapper.toDto(surveyRepository.save(surveyMapper.toEntity(createSurvey)));
//    }

    @Override
    public CreateSurvey createSurvey(CreateSurvey createSurvey) {
        // Fetch the Owner entity using ownerId
        Owner owner = ownerRepository.findById(createSurvey.ownerId())
                .orElseThrow(() -> new RuntimeException("Owner not found with id " + createSurvey.ownerId()));

        // Create Survey entity and set the owner
        Survey survey = surveyMapper.toEntity(createSurvey);
        survey.setOwner(owner); // Set the owner here

        return surveyMapper.toDto(surveyRepository.save(survey));
    }

    @Override
    public CreateSurvey getSurveyById(Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found with id " + id));
        return surveyMapper.toDto(survey);
    }

    @Override
    public List<CreateSurvey> getAllSurveys() {
        return ((List<Survey>) surveyRepository.findAll()).stream()
                .map(surveyMapper::toDto)
                .collect(Collectors.toList());
    }
//    @Override
//    public CreateSurvey updateSurvey(Long id, CreateSurvey createSurvey) {
//        CreateSurvey existingSurvey = getSurveyById(id);
//        surveyMapper.updateSurveyFromRequest(createSurvey, existingSurvey);
//        return surveyRepository.save(existingSurvey);
//    }
//
    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }
}

