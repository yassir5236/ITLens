package org.yassir.itlens.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yassir.itlens.dto.Survey.CreateSurvey;
import org.yassir.itlens.service.ISurveyService;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    private final ISurveyService surveyService;

    @Autowired
    public SurveyController(ISurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public ResponseEntity<CreateSurvey> createSurvey(@RequestBody CreateSurvey createSurvey) {
        CreateSurvey createdSurvey = surveyService.createSurvey(createSurvey);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSurvey);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateSurvey> getSurveyById(@PathVariable Long id) {
        CreateSurvey survey = surveyService.getSurveyById(id);
        return ResponseEntity.ok(survey);
    }

    @GetMapping
    public ResponseEntity<List<CreateSurvey>> getAllSurveys() {
        List<CreateSurvey> surveys = surveyService.getAllSurveys();
        return ResponseEntity.ok(surveys);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<CreateSurvey> updateSurvey(@PathVariable Long id, @RequestBody CreateSurvey createSurvey) {
//        CreateSurvey updatedSurvey = surveyService.updateSurvey(id, createSurvey); // Uncomment and adjust in your service
//        return ResponseEntity.ok(updatedSurvey);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
        return ResponseEntity.noContent().build();
    }
}

