package org.yassir.itlens.service.Impl;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.yassir.itlens.mapper.owner.OwnerMapper;
import org.yassir.itlens.dto.Owner.OwnerRequest;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.mapper.owner.OwnerMapper;
import org.yassir.itlens.model.Entity.Owner;
import org.yassir.itlens.repository.OwnerRepository;
import org.yassir.itlens.service.IOwnerSerice;

import java.util.Optional;

@Service
public class OwnerServiceImpl  implements IOwnerSerice  {

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

        try {
            Owner savedOwner = ownerRepository.save(owner);
            return ownerMapper.toOwnerResponse(savedOwner);
        } catch (ConstraintViolationException e) {
            throw new RuntimeException("Owner with name '" + ownerRequest.name() + "' already exists.");
        }
    }

    @Override
    public OwnerResponse getOwnerById(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        return ownerMapper.toOwnerResponse(owner);
    }


}
