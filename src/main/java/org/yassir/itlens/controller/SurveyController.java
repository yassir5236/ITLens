package org.yassir.itlens.controller;


import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yassir.itlens.dto.Participation.ParticipationRequest;
import org.yassir.itlens.dto.Survey.SurveyRequest;
import org.yassir.itlens.dto.Survey.SurveyResponse;
import org.yassir.itlens.dto.Survey.SurveyUpdate;
import org.yassir.itlens.service.ISurveyService;
import org.yassir.itlens.validation.IdExist;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
@Validated
public class SurveyController {

    private final ISurveyService surveyService;

    @Autowired
    public SurveyController(ISurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public ResponseEntity<SurveyResponse> createSurvey(@Valid @RequestBody SurveyRequest surveyRequest) {
        SurveyResponse surveyResponse = surveyService.createSurvey(surveyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(surveyResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyResponse> getSurveyById(@IdExist(message = "Survey not found") @PathVariable Long id) {
        SurveyResponse survey = surveyService.getSurveyById(id);
        return ResponseEntity.ok(survey);
    }

    @GetMapping
    public ResponseEntity<List<SurveyResponse>> getAllSurveys() {
        List<SurveyResponse> surveys = surveyService.getAllSurveys();
        return ResponseEntity.ok(surveys);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyResponse> updateSurvey(@IdExist(message = "Survey not found") @PathVariable Long id, @Valid @RequestBody SurveyUpdate surveyUpdate) {
        SurveyResponse updatedSurvey = surveyService.updateSurvey(id, surveyUpdate);
        return ResponseEntity.ok(updatedSurvey);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@IdExist(message = "Survey not found") @PathVariable Long id) {
        surveyService.deleteSurvey(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{surveyId}/participate")
    public ResponseEntity<String> participateInSurvey(@IdExist(message = "Survey not found") @PathVariable Long surveyId, @RequestBody ParticipationRequest participationRequest) {
        surveyService.participateInSurvey(surveyId, participationRequest);
        return ResponseEntity.ok("Participation enregistrée avec succès.");
    }




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");
        });
        return ResponseEntity.badRequest().body(errors.toString());
    }




    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .findFirst()
                .orElse("Erreur de validation");

        return ResponseEntity.badRequest().body(errorMessage);
    }

}

