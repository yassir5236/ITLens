//package org.yassir.itlens.controller;
//
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.yassir.itlens.dto.Subject.SubjectRequest;
//import org.yassir.itlens.dto.Subject.SubjectResponse;
//import org.yassir.itlens.dto.Survey.SurveyRequest;
//import org.yassir.itlens.dto.Survey.SurveyResponse;
//import org.yassir.itlens.service.ISubject;
//
//@RestController
//@RequestMapping("/api/subjects")
//
//public class SubjectController {
//    private  final ISubject subject;
//    @Autowired
//    public SubjectController(ISubject subject) {
//        this.subject = subject;
//    }
//
//    @PostMapping
//    public ResponseEntity<SubjectResponse> createSubject( @RequestBody SubjectRequest SubjectRequest) {
//        SubjectResponse subjectResponse = subject.createSubject(SubjectRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(subjectResponse);
//    }
//}






package org.yassir.itlens.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yassir.itlens.dto.Subject.SubjectRequest;
import org.yassir.itlens.dto.Subject.SubjectResponse;
import org.yassir.itlens.dto.Subject.SubjectUpdate;
import org.yassir.itlens.service.ISubject;
import org.yassir.itlens.validation.IdExist;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@Validated

public class SubjectController {

    private final ISubject subjectService;

    @Autowired
    public SubjectController(ISubject subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<SubjectResponse> createSubject(@Valid @RequestBody SubjectRequest subjectRequest) {
        SubjectResponse subjectResponse = subjectService.createSubject(subjectRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponse> getSubjectById(@IdExist (message ="subject not found ")@PathVariable Long id) {
        SubjectResponse subjectResponse = subjectService.getSubjectById(id);
        return ResponseEntity.ok(subjectResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponse> updateSubject(@PathVariable Long id, @Valid @RequestBody SubjectUpdate subjectUpdate) {
        SubjectResponse updatedSubjectResponse = subjectService.updateSubject(id, subjectUpdate);
        return ResponseEntity.ok(updatedSubjectResponse);
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponse>> getAllSubjects() {
        List<SubjectResponse> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@IdExist(message = "subject not found ")@PathVariable Long id) {
        subjectService.deleteSubject(id);
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
