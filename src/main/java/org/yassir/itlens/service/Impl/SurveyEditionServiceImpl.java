package org.yassir.itlens.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionRequest;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionResponse;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionUpdate;
import org.yassir.itlens.mapper.SurveyEdition.SurveyEditionMapper;
import org.yassir.itlens.model.Entity.SurveyEdition;
import org.yassir.itlens.repository.ISurveyEditionRepository;
import org.yassir.itlens.service.ISurveyEdition;

@Service
public class SurveyEditionServiceImpl implements ISurveyEdition {

    private final ISurveyEditionRepository surveyEditionRepository;
    private final SurveyEditionMapper surveyEditionMapper;

    @Autowired
    public SurveyEditionServiceImpl(ISurveyEditionRepository surveyEditionRepository, SurveyEditionMapper surveyEditionMapper) {
        this.surveyEditionRepository = surveyEditionRepository;
        this.surveyEditionMapper = surveyEditionMapper;
    }

    @Override
    public SurveyEditionResponse createSurveyEdition(SurveyEditionRequest surveyRequest) {
        SurveyEdition surveyEdition = surveyEditionMapper.toEntity(surveyRequest);

        SurveyEdition savedSurveyEdition = surveyEditionRepository.save(surveyEdition);

        return surveyEditionMapper.toResponse(savedSurveyEdition);
    }

    @Override
    public SurveyEditionResponse getSurveyEditionById(Long id) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SurveyEdition not found with id " + id));
        return surveyEditionMapper.toResponse(surveyEdition);
    }

//    @Override
//    public List<SurveyEditionResponse> getAllSurveyEditions() {
//        return surveyEditionRepository.findAll().stream()
//                .map(surveyEditionMapper::toResponse)
//                .collect(Collectors.toList());
//    }

//    @Override
//    public SurveyEditionResponse updateSurveyEdition(Long id, SurveyEditionUpdate surveyUpdate) {
//        SurveyEdition existingSurveyEdition = surveyEditionRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("SurveyEdition not found with id " + id));
//
//        existingSurveyEdition.setCreationDate(surveyUpdate.creationDate());
//        existingSurveyEdition.setStartDate(surveyUpdate.startDate());
//        existingSurveyEdition.setEndDate(surveyUpdate.EndDate());
//        existingSurveyEdition.setYear(surveyUpdate.year());
//
//        SurveyEdition updatedSurveyEdition = surveyEditionRepository.save(existingSurveyEdition);
//        return surveyEditionMapper.toResponse(updatedSurveyEdition);
//    }

    @Override
    public void deleteSurveyEdition(Long id) {
        surveyEditionRepository.deleteById(id);
    }
}
