package org.yassir.itlens.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yassir.itlens.model.Entity.Answer;
import org.yassir.itlens.model.Entity.Question;

import java.util.Optional;

@Repository
public interface IAnswerRepository extends CrudRepository<Answer, Long> {
    Answer findByQuestionId(Long questionId);
//    Optional<Answer> findByIdAndQuestionId(Long id, Long questionId);


}
