package org.yassir.itlens.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yassir.itlens.model.Entity.SurveyEdition;

@Repository
public interface ISurveyEditionRepository extends CrudRepository<SurveyEdition, Long> {
}
