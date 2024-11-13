package org.yassir.itlens.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yassir.itlens.dto.Answer.AnswerRequest;
import org.yassir.itlens.dto.Answer.AnswerResponse;
import org.yassir.itlens.dto.Answer.AnswerResponse;
import org.yassir.itlens.dto.Answer.AnswerUpdate;
import org.yassir.itlens.service.Impl.AnswerServiceImpl;
import org.yassir.itlens.validation.IdExist;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
@Validated
public class AnswerController {
    private final AnswerServiceImpl answerService;

    public AnswerController(AnswerServiceImpl answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/{answerId}/select")
    public ResponseEntity<String> selectAnswer(@PathVariable Long answerId) {
        answerService.incrementSelectionCount(answerId);
        return ResponseEntity.ok("Answer selection count incremented successfully");
    }


    @PostMapping
    public ResponseEntity<AnswerResponse> addAnswer(@Valid @RequestBody AnswerRequest answerRequest) {
        answerService.createAnswer(answerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponse> getAnswerById(@IdExist(message = "answer noooot found") @PathVariable Long id) {
        AnswerResponse answerResponse = answerService.getAnswerById(id);
        return ResponseEntity.ok(answerResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerResponse> updateAnswer(
            @IdExist(message = "answer noooot found") @PathVariable Long id,
            @Valid @RequestBody AnswerUpdate answerUpdate
    ) {
        AnswerResponse updatedAnswer = answerService.updateAnswer(id, answerUpdate);
        return ResponseEntity.ok(updatedAnswer);
    }

    @GetMapping
    public ResponseEntity<List<AnswerResponse>> getAllAnswers() {
        List<AnswerResponse> answers = answerService.getAllAnswers();
        return ResponseEntity.ok(answers);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AnswerResponse> deleteAnswer(@IdExist(message = "answer introvable with this id") @PathVariable Long id) {
        answerService.deleteAnswer(id);
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
