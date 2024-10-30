package org.yassir.itlens.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yassir.itlens.model.Entity.Survey;

import java.util.Optional;

@Repository
public interface ISurveyRepository extends CrudRepository<Survey, Long> {
    Optional<Survey> findFirstByTitle(String title);

}
