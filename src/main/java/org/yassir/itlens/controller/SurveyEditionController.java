package org.yassir.itlens.controller;


import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionRequest;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionResponse;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionUpdate;
import org.yassir.itlens.service.ISurveyEdition;
import org.yassir.itlens.validation.IdExist;

import java.util.List;

@RestController
@RequestMapping("/api/survey-editions")
@Validated
public class SurveyEditionController {

    private final ISurveyEdition surveyEditionService;

    @Autowired
    public SurveyEditionController(ISurveyEdition surveyEditionService) {
        this.surveyEditionService = surveyEditionService;
    }

    @PostMapping
    public ResponseEntity<SurveyEditionResponse> createSurveyEdition(@Valid @RequestBody SurveyEditionRequest surveyEditionRequest) {
        SurveyEditionResponse surveyEditionResponse = surveyEditionService.createSurveyEdition(surveyEditionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(surveyEditionResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyEditionResponse> getSurveyEditionById(@IdExist (message = "Survey edition not found")@PathVariable Long id) {
        SurveyEditionResponse surveyEditionResponse = surveyEditionService.getSurveyEditionById(id);
        return ResponseEntity.ok(surveyEditionResponse);
    }

    @GetMapping
    public ResponseEntity<List<SurveyEditionResponse>> getAllSurveyEditions() {
        List<SurveyEditionResponse> surveyEditions = surveyEditionService.getAllSurveyEditions();
        return ResponseEntity.ok(surveyEditions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyEditionResponse> updateSurveyEdition(@IdExist (message = "Survey edition not found") @PathVariable Long id, @Valid @RequestBody SurveyEditionUpdate surveyEditionUpdate) {
        SurveyEditionResponse updatedSurveyEdition = surveyEditionService.updateSurveyEdition(id, surveyEditionUpdate);
        return ResponseEntity.ok(updatedSurveyEdition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurveyEdition(@IdExist (message = "Survey edition not found")@PathVariable Long id) {
        surveyEditionService.deleteSurveyEdition(id);
        return ResponseEntity.noContent().build();
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
