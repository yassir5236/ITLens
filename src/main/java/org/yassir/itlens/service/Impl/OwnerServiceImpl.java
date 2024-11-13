package org.yassir.itlens.service.Impl;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.yassir.itlens.mapper.owner.OwnerMapper;
import org.yassir.itlens.dto.Owner.OwnerRequest;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.dto.Owner.OwnerUpdate;
import org.yassir.itlens.mapper.owner.OwnerMapper;
import org.yassir.itlens.model.Entity.Owner;
import org.yassir.itlens.repository.OwnerRepository;
import org.yassir.itlens.service.IOwnerService;

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

    @Override
    public List<OwnerResponse> getAllOwners() {
        Iterable<Owner> ownersIterable = ownerRepository.findAll();
        List<OwnerResponse> ownersList = new ArrayList<>(); // Create List<OwnerResponse>

        ownersIterable.forEach(owner -> {
            OwnerResponse response = ownerMapper.toOwnerResponse(owner); // Map Owner to OwnerResponse
            ownersList.add(response); // Add the mapped response to the list
        });

        return ownersList;
    }


    @Override
    public void deleteOwner(Long ownerId) {
        ownerRepository.deleteById(ownerId);
    }


}
