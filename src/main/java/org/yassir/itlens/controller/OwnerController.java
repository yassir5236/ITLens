package org.yassir.itlens.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yassir.itlens.dto.Owner.OwnerRequest;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.model.Entity.Owner;
import org.yassir.itlens.service.IOwnerSerice;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private IOwnerSerice ownerService;

    @Autowired
    public OwnerController(IOwnerSerice ownerService) {
        this.ownerService = ownerService;
    }



    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(@Valid  @RequestBody OwnerRequest ownerRequest) {
        OwnerResponse createdOwner = ownerService.createOwner(ownerRequest);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> getOwnerById(@Valid @PathVariable Long id) {
        OwnerResponse ownerResponse = ownerService.getOwnerById(id);
        return ResponseEntity.ok(ownerResponse);
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
