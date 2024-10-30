package org.yassir.itlens.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yassir.itlens.model.Entity.Owner;

import java.util.Optional;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Optional<Owner> findByName(String name);

}
