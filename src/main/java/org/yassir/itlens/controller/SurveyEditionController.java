package org.yassir.itlens.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionRequest;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionResponse;
import org.yassir.itlens.dto.SurveyEdition.SurveyEditionUpdate;
import org.yassir.itlens.service.ISurveyEdition;

import java.util.List;

@RestController
@RequestMapping("/api/survey-editions")
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
    public ResponseEntity<SurveyEditionResponse> getSurveyEditionById(@PathVariable Long id) {
        SurveyEditionResponse surveyEditionResponse = surveyEditionService.getSurveyEditionById(id);
        return ResponseEntity.ok(surveyEditionResponse);
    }

//    @GetMapping
//    public ResponseEntity<List<SurveyEditionResponse>> getAllSurveyEditions() {
//        List<SurveyEditionResponse> surveyEditions = surveyEditionService.getAllSurveyEditions();
//        return ResponseEntity.ok(surveyEditions);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<SurveyEditionResponse> updateSurveyEdition(@PathVariable Long id, @Valid @RequestBody SurveyEditionUpdate surveyEditionUpdate) {
//        SurveyEditionResponse updatedSurveyEdition = surveyEditionService.updateSurveyEdition(id, surveyEditionUpdate);
//        return ResponseEntity.ok(updatedSurveyEdition);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurveyEdition(@PathVariable Long id) {
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
}
