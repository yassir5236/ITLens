package org.yassir.itlens.controller;


import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yassir.itlens.dto.SubSubject.SubSubjectRequest;
import org.yassir.itlens.dto.SubSubject.SubSubjectResponse;
import org.yassir.itlens.dto.SubSubject.SubSubjectUpdate;
import org.yassir.itlens.service.ISubSubject;
import org.yassir.itlens.validation.IdExist;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/sub-subjects")
@Validated
public class SubSubjectController {

    private final ISubSubject subSubjectService;

    @Autowired
    public SubSubjectController(ISubSubject subSubjectService) {
        this.subSubjectService = subSubjectService;
    }

    @PostMapping
    public ResponseEntity<SubSubjectResponse> createSubSubject(@Valid @RequestBody SubSubjectRequest subSubjectRequest) {
        SubSubjectResponse subSubjectResponse = subSubjectService.createSubSubject(subSubjectRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(subSubjectResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubSubjectResponse> getSubSubjectById(@IdExist(message =" subSubject not found ") @PathVariable Long id) {
        SubSubjectResponse subSubjectResponse = subSubjectService.getSubSubjectById(id);
        return ResponseEntity.ok(subSubjectResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SubSubjectResponse> updateSubSubject(@IdExist(message = "subSubject not found" )@PathVariable Long id, @Valid @RequestBody SubSubjectUpdate subSubjectUpdate) {
        SubSubjectResponse updatedSubSubjectResponse = subSubjectService.updateSubSubject(id, subSubjectUpdate);
        return ResponseEntity.ok(updatedSubSubjectResponse);
    }

    @GetMapping
    public ResponseEntity<List<SubSubjectResponse>> getAllSubSubjects() {
        List<SubSubjectResponse> subSubjects = subSubjectService.getAllSubSubjects();
        return ResponseEntity.ok(subSubjects);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubSubject(@IdExist (message = "SubSubject not found")@PathVariable Long id) {
        subSubjectService.deleteSubSubject(id);
        return ResponseEntity.noContent().build();
    }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        String errorMessage = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(fieldError -> fieldError.getDefaultMessage()) // Extracts the concise message
//                .findFirst() // You can change this to return all messages if needed
//                .orElse("Validation error occurred");
//
//        return ResponseEntity.badRequest().body(errorMessage);
//    }



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
