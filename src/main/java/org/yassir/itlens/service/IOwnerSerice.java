package org.yassir.itlens.service;

import org.yassir.itlens.dto.Owner.OwnerRequest;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.model.Entity.Owner;

public interface IOwnerSerice {
    OwnerResponse createOwner(OwnerRequest ownerRequest);
     OwnerResponse getOwnerById(Long ownerId) ;
}
