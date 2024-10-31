package org.yassir.itlens.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.dto.Owner.OwnerUpdate;
import org.yassir.itlens.dto.Survey.SurveyRequest;
import org.yassir.itlens.dto.Survey.SurveyResponse;
import org.yassir.itlens.dto.Survey.SurveyUpdate;
import org.yassir.itlens.mapper.survey.SurveyMapper;
import org.yassir.itlens.model.Entity.Owner;
import org.yassir.itlens.model.Entity.Survey;
import org.yassir.itlens.repository.ISurveyRepository;
import org.yassir.itlens.repository.OwnerRepository;
import org.yassir.itlens.service.ISurveyService;

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



    @Override
    public SurveyResponse createSurvey(SurveyRequest surveyRequest) {

      Survey survey = surveyMapper.toEntity(surveyRequest);

       Survey savedSurvey = surveyRepository.save(survey);

        return surveyMapper.toSurveyResponse(savedSurvey);
    }





    @Override
    public SurveyResponse getSurveyById(Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found with id " + id));
        return surveyMapper.toSurveyResponse(survey);
    }

//    @Override
//    public List<CreateSurvey> getAllSurveys() {
//        return ((List<Survey>) surveyRepository.findAll()).stream()
//                .map(surveyMapper::toDto)
//                .collect(Collectors.toList());
//    }
@Override
public SurveyResponse updateSurvey(Long id, SurveyUpdate surveyUpdate) {
    Survey existingSurvey = surveyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Survey not found with id " + id));
    Owner owner = ownerRepository.findById(surveyUpdate.ownerId())
            .orElseThrow(() -> new RuntimeException("Owner not found with id " + surveyUpdate.ownerId()));


    existingSurvey.setDescription(surveyUpdate.description());
    existingSurvey.setTitle(surveyUpdate.title());
    existingSurvey.setOwner(owner);

    Survey updatedSurvey = surveyRepository.save(existingSurvey);
    return surveyMapper.toSurveyResponse(updatedSurvey);
}






    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }
}

