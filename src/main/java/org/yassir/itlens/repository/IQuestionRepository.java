package org.yassir.itlens.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yassir.itlens.model.Entity.Question;

import java.util.Optional;

@Repository
public interface IQuestionRepository extends CrudRepository<Question, Long> {
//    Optional<Question> findByIdAndSurveyId(Long id, Long surveyId);

}
