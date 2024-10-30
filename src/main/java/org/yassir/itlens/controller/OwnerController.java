package org.yassir.itlens.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//    @PostMapping
//    public ResponseEntity<OwnerResponse> createOwner(@RequestBody OwnerRequest ownerRequest) {
//        OwnerResponse createdOwner = ownerService.createOwner(ownerRequest);
//        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
//    }


    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(@RequestBody OwnerRequest ownerRequest) {
        OwnerResponse createdOwner = ownerService.createOwner(ownerRequest);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> getOwnerById(@PathVariable Long id) {
        OwnerResponse ownerResponse = ownerService.getOwnerById(id);
        return ResponseEntity.ok(ownerResponse);
    }
}
