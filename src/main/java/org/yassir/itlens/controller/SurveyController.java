package org.yassir.itlens.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yassir.itlens.dto.Survey.SurveyRequest;
import org.yassir.itlens.dto.Survey.SurveyResponse;
import org.yassir.itlens.dto.Survey.SurveyUpdate;
import org.yassir.itlens.service.ISurveyService;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    private final ISurveyService surveyService;

    @Autowired
    public SurveyController(ISurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public ResponseEntity<SurveyResponse> createSurvey(@Valid  @RequestBody SurveyRequest surveyRequest) {
        SurveyResponse surveyResponse = surveyService.createSurvey(surveyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(surveyResponse);
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CreateSurvey> getSurveyById(@PathVariable Long id) {
//        CreateSurvey survey = surveyService.getSurveyById(id);
//        return ResponseEntity.ok(survey);
//    }

//    @GetMapping
//    public ResponseEntity<List<CreateSurvey>> getAllSurveys() {
//        List<CreateSurvey> surveys = surveyService.getAllSurveys();
//        return ResponseEntity.ok(surveys);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyResponse> updateSurvey(@PathVariable Long id,@Valid  @RequestBody SurveyUpdate surveyUpdate) {
        SurveyResponse updatedSurvey = surveyService.updateSurvey(id, surveyUpdate);
        return ResponseEntity.ok(updatedSurvey);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
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

