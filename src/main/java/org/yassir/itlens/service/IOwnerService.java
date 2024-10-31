package org.yassir.itlens.service;

import org.yassir.itlens.dto.Owner.OwnerRequest;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.dto.Owner.OwnerUpdate;

import java.util.List;

public interface IOwnerService {
    OwnerResponse createOwner(OwnerRequest ownerRequest);
     OwnerResponse getOwnerById(Long ownerId) ;
     OwnerResponse updateOwner(Long id, OwnerUpdate ownerUpdate);
     List<OwnerResponse> getAllOwners() ;
     void deleteOwner(Long ownerId);
    }
