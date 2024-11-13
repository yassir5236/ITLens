package org.yassir.itlens.service.Impl;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionRequest;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionResponse;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionUpdate;
import org.yassir.itlens.mapper.SurveyEdition.SurveyEditionMapper;
import org.yassir.itlens.model.Entity.Survey;
import org.yassir.itlens.model.Entity.SurveyEdition;
import org.yassir.itlens.repository.ISurveyEditionRepository;
import org.yassir.itlens.repository.ISurveyRepository;
import org.yassir.itlens.service.ISurveyEdition;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyEditionServiceImpl implements ISurveyEdition {

    private final ISurveyEditionRepository surveyEditionRepository;
    private final SurveyEditionMapper surveyEditionMapper;
    private final ISurveyRepository surveyRepository;

    @Autowired
    public SurveyEditionServiceImpl(ISurveyEditionRepository surveyEditionRepository, SurveyEditionMapper surveyEditionMapper, ISurveyRepository surveyRepository) {
        this.surveyEditionRepository = surveyEditionRepository;
        this.surveyEditionMapper = surveyEditionMapper;
        this.surveyRepository = surveyRepository;
    }

    @Override
    public SurveyEditionResponse createSurveyEdition(SurveyEditionRequest surveyRequest) {
        Survey survey = surveyRepository.findById(surveyRequest.surveyId())
                .orElseThrow(() -> new EntityNotFoundException("Survey not found"));
        SurveyEdition surveyEdition = surveyEditionMapper.toEntity(surveyRequest, survey);

        SurveyEdition savedSurveyEdition = surveyEditionRepository.save(surveyEdition);

        return surveyEditionMapper.toResponse(savedSurveyEdition);
    }

    @Override
    public SurveyEditionResponse getSurveyEditionById(Long id) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SurveyEdition not found with id " + id));
        return surveyEditionMapper.toResponse(surveyEdition);
    }

    @Override
    public List<SurveyEditionResponse> getAllSurveyEditions() {

        Iterable<SurveyEdition> surveyEditions = surveyEditionRepository.findAll();
        List<SurveyEditionResponse> surveyEditionResponses = new ArrayList<>();
        surveyEditions.forEach(surveyEdition -> {
            surveyEditionResponses.add(surveyEditionMapper.toResponse(surveyEdition));
        });
        return surveyEditionResponses;
    }

    @Override
    public SurveyEditionResponse updateSurveyEdition(Long id, SurveyEditionUpdate surveyUpdate) {
        SurveyEdition existingSurveyEdition = surveyEditionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SurveyEdition not found with id " + id));

        existingSurveyEdition.setCreationDate(surveyUpdate.creationDate());
        existingSurveyEdition.setStartDate(surveyUpdate.startDate());
        existingSurveyEdition.setEndDate(surveyUpdate.endDate());
        existingSurveyEdition.setYear(surveyUpdate.year());

        SurveyEdition updatedSurveyEdition = surveyEditionRepository.save(existingSurveyEdition);
        return surveyEditionMapper.toResponse(updatedSurveyEdition);
    }

    @Override
    public void deleteSurveyEdition(Long id) {
        surveyEditionRepository.deleteById(id);
    }
}
