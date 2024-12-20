package org.yassir.itlens.service.Impl;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
//import org.yassir.itlens.mapper.owner.OwnerMapper;
import org.yassir.itlens.dto.Owner.OwnerRequest;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.dto.Owner.OwnerUpdate;
import org.yassir.itlens.mapper.owner.OwnerMapper;
import org.yassir.itlens.model.Entity.Owner;
import org.yassir.itlens.repository.OwnerRepository;
import org.yassir.itlens.service.IOwnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements IOwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    @Override
    public OwnerResponse createOwner(OwnerRequest ownerRequest) {
        Owner owner = ownerMapper.toEntity(ownerRequest);

        Owner savedOwner = ownerRepository.save(owner);
        return ownerMapper.toOwnerResponse(savedOwner);

    }

    @Override
    public OwnerResponse getOwnerById(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner nottt found"));
        return ownerMapper.toOwnerResponse(owner);
    }


    @Override
    public OwnerResponse updateOwner(Long ownerId, OwnerUpdate ownerUpdate) {

        Owner existingOwner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        existingOwner.setName(ownerUpdate.name());
        Owner updatedOwner = ownerRepository.save(existingOwner);

        return ownerMapper.toOwnerResponse(updatedOwner);
    }


    //        Iterable<Owner> ownersIterable = ownerRepository.findAll();
//        List<OwnerResponse> ownersList = new ArrayList<>();
//
//        ownersIterable.forEach(owner -> {
//            OwnerResponse response = ownerMapper.toOwnerResponse(owner);
//            ownersList.add(response);
//        });
//
//        return ownersList;


    @Override
    public Page<OwnerResponse> getAllOwners(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Owner> ownersPage = ownerRepository.findAll(pageable);
        return ownersPage.map(ownerMapper::toOwnerResponse);


    }


    @Override
    public void deleteOwner(Long ownerId) {
        ownerRepository.deleteById(ownerId);
    }


}
