package org.yassir.itlens.service.Impl;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.yassir.itlens.dto.Owner.EmbeddedOwner;
import org.yassir.itlens.dto.Owner.OwnerRequest;
import org.yassir.itlens.dto.Owner.OwnerResponse;
import org.yassir.itlens.dto.Owner.OwnerUpdate;
import org.yassir.itlens.mapper.owner.OwnerMapper;
import org.yassir.itlens.model.Entity.Owner;
import org.yassir.itlens.repository.OwnerRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OwnerServiceImplTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private OwnerMapper ownerMapper;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOwner_ShouldReturnOwnerResponse() {
        OwnerRequest ownerRequest = new OwnerRequest("John Doe");
        Owner owner = new Owner();
        owner.setName("John Doe");
        Owner savedOwner = new Owner();
        savedOwner.setId(1L);
        savedOwner.setName("John Doe");

        OwnerResponse expectedResponse = new OwnerResponse(1L, "John Doe", new ArrayList<>());

        when(ownerMapper.toEntity(ownerRequest)).thenReturn(owner);
        when(ownerRepository.save(owner)).thenReturn(savedOwner);
        when(ownerMapper.toOwnerResponse(savedOwner)).thenReturn(expectedResponse);

        OwnerResponse response = ownerService.createOwner(ownerRequest);

        assertNotNull(response);
        assertEquals(expectedResponse, response);

        verify(ownerMapper).toEntity(ownerRequest);
        verify(ownerRepository).save(owner);
        verify(ownerMapper).toOwnerResponse(savedOwner);
    }


    @Test
    void getOwnerById_ShouldReturnOwnerResponse_WhenOwnerExists() {
        Long ownerId = 1L;
        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setName("Jane Doe");

        OwnerResponse expectedResponse = new OwnerResponse(ownerId, "Jane Doe", new ArrayList<>());

        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(owner));
        when(ownerMapper.toOwnerResponse(owner)).thenReturn(expectedResponse);

        OwnerResponse response = ownerService.getOwnerById(ownerId);

        assertNotNull(response);
        assertEquals(expectedResponse, response);

        verify(ownerRepository).findById(ownerId);
        verify(ownerMapper).toOwnerResponse(owner);
    }

    @Test
    void getOwnerById_ShouldThrowException_WhenOwnerDoesNotExist() {
        Long ownerId = 1L;

        when(ownerRepository.findById(ownerId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ownerService.getOwnerById(ownerId);
        });

        assertEquals("Owner nottt found", exception.getMessage());

        verify(ownerRepository).findById(ownerId);
        verifyNoInteractions(ownerMapper);
    }

    @Test
    void updateOwner_ShouldReturnUpdatedOwnerResponse() {
        Long ownerId = 1L;
        OwnerUpdate ownerUpdate = new OwnerUpdate("Updated Name");
        Owner existingOwner = new Owner();
        existingOwner.setId(ownerId);
        existingOwner.setName("Old Name");

        Owner updatedOwner = new Owner();
        updatedOwner.setId(ownerId);
        updatedOwner.setName("Updated Name");

        OwnerResponse expectedResponse = new OwnerResponse(ownerId, "Updated Name", new ArrayList<>());

        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(existingOwner));
        when(ownerRepository.save(existingOwner)).thenReturn(updatedOwner);
        when(ownerMapper.toOwnerResponse(updatedOwner)).thenReturn(expectedResponse);

        OwnerResponse response = ownerService.updateOwner(ownerId, ownerUpdate);

        assertNotNull(response);
        assertEquals(expectedResponse, response);

        verify(ownerRepository).findById(ownerId);
        verify(ownerRepository).save(existingOwner);
        verify(ownerMapper).toOwnerResponse(updatedOwner);
    }

    @Test
    void getAllOwners_ShouldReturnPagedListOfOwnerResponses() {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setName("Owner 1");

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setName("Owner 2");

        List<Owner> owners = Arrays.asList(owner1, owner2);
        OwnerResponse response1 = new OwnerResponse(1L, "Owner 1", new ArrayList<>());
        OwnerResponse response2 = new OwnerResponse(2L, "Owner 2", new ArrayList<>());

        int page = 0;
        int size = 2;
        Pageable pageable = PageRequest.of(page, size);
        Page<Owner> ownersPage = new PageImpl<>(owners, pageable, owners.size());

        when(ownerRepository.findAll(pageable)).thenReturn(ownersPage);
        when(ownerMapper.toOwnerResponse(owner1)).thenReturn(response1);
        when(ownerMapper.toOwnerResponse(owner2)).thenReturn(response2);

        Page<OwnerResponse> responses = ownerService.getAllOwners(page, size);

        assertNotNull(responses);
        assertEquals(2, responses.getContent().size());
        assertEquals(response1, responses.getContent().get(0));
        assertEquals(response2, responses.getContent().get(1));

        verify(ownerRepository).findAll(pageable);
        verify(ownerMapper).toOwnerResponse(owner1);
        verify(ownerMapper).toOwnerResponse(owner2);
    }

    @Test
    void deleteOwner_ShouldCallRepositoryDeleteById() {
        Long ownerId = 1L;

        doNothing().when(ownerRepository).deleteById(ownerId);

        ownerService.deleteOwner(ownerId);

        verify(ownerRepository).deleteById(ownerId);
    }
}
