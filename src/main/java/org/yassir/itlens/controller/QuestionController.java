package org.yassir.itlens.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yassir.itlens.dto.Question.QuestionResponse;
import org.yassir.itlens.dto.Question.QuestionUpdate;
import org.yassir.itlens.dto.Question.QuestionRequest;
import org.yassir.itlens.dto.Question.QuestionResponse;
import org.yassir.itlens.service.Impl.QuestionServiceImpl;
import org.yassir.itlens.validation.IdExist;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@Validated
public class QuestionController {
    private final QuestionServiceImpl questionService;

    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<QuestionResponse> addQuestion(@Validated @RequestBody QuestionRequest questionRequest) {
        questionService.createQuestion(questionRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }



    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getQuestionById(@IdExist(message = "question noooot found") @PathVariable Long id) {
        QuestionResponse questionResponse = questionService.getQuestionById(id);
        return ResponseEntity.ok(questionResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponse> updateQuestion(
            @IdExist(message = "question noooot found") @PathVariable Long id,
            @Valid @RequestBody QuestionUpdate questionUpdate
    ) {
        QuestionResponse updatedQuestion = questionService.updateQuestion(id, questionUpdate);
        return ResponseEntity.ok(updatedQuestion);
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponse>> getAllQuestions() {
        List<QuestionResponse> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuestionResponse> deleteQuestion(@IdExist(message = "question introvable with this id") @PathVariable Long id) {
        questionService.deleteQuestion(id);
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
