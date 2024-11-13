package org.yassir.itlens.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yassir.itlens.model.Entity.Subject;
@Repository

public interface ISubjectRepository extends CrudRepository<Subject,Long> {
}
