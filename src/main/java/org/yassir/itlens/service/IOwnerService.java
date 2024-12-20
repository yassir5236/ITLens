package org.yassir.itlens.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.yassir.itlens.dto.Owner.OwnerRequest;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.dto.Owner.OwnerUpdate;

import java.util.List;

public interface IOwnerService {
    OwnerResponse createOwner(OwnerRequest ownerRequest);

    OwnerResponse getOwnerById(Long ownerId);

    OwnerResponse updateOwner(Long id, OwnerUpdate ownerUpdate);

    Page<OwnerResponse> getAllOwners(int page, int size);

    void deleteOwner(Long ownerId);
}
