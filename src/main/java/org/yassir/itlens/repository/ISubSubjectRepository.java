package org.yassir.itlens.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yassir.itlens.model.Entity.SubSubject;

@Repository
public interface ISubSubjectRepository extends CrudRepository<SubSubject,Long> {
}
