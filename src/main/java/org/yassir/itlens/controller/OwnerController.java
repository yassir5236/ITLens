package org.yassir.itlens.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yassir.itlens.dto.Owner.OwnerRequest;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.dto.Owner.OwnerUpdate;
import org.yassir.itlens.model.Entity.Owner;
import org.yassir.itlens.repository.OwnerRepository;
import org.yassir.itlens.service.IOwnerService;
import org.yassir.itlens.validation.IdExist;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
@Validated
public class OwnerController {

    private final OwnerRepository ownerRepository;
    private IOwnerService ownerService;

    @Autowired
    public OwnerController(IOwnerService ownerService, OwnerRepository ownerRepository) {
        this.ownerService = ownerService;
        this.ownerRepository = ownerRepository;
    }



    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(@Valid  @RequestBody OwnerRequest ownerRequest) {
        OwnerResponse createdOwner = ownerService.createOwner(ownerRequest);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> getOwnerById(@IdExist(message = "owner noooot found") @PathVariable Long id) {
        OwnerResponse ownerResponse = ownerService.getOwnerById(id);
        return ResponseEntity.ok(ownerResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponse> updateOwner(
           @Valid @PathVariable Long id,
            @Valid @RequestBody OwnerUpdate ownerUpdate
    ) {
        OwnerResponse updatedOwner = ownerService.updateOwner(id, ownerUpdate);
        return ResponseEntity.ok(updatedOwner);
    }

    @GetMapping
    public ResponseEntity<List<OwnerResponse>> getAllOwners() {
        List<OwnerResponse> owners = ownerService.getAllOwners();
        return ResponseEntity.ok(owners);

    }

    @DeleteMapping("/{id}")
   public ResponseEntity<OwnerResponse> deleteOwner(@IdExist(message = "owner introvable with this id") @PathVariable Long id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
   }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        StringBuilder errors = new StringBuilder();
//        ex.getBindingResult().getFieldErrors().forEach(error -> {
//            errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");
//        });
//        return ResponseEntity.badRequest().body(errors.toString());
//    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .findFirst()
                .orElse("Erreur de validation");

        return ResponseEntity.badRequest().body(errorMessage);
    }



}
