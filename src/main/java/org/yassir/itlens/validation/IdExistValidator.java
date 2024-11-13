
package org.yassir.itlens.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yassir.itlens.repository.ISubjectRepository;
import org.yassir.itlens.repository.ISurveyEditionRepository;
import org.yassir.itlens.repository.ISurveyRepository;
import org.yassir.itlens.repository.OwnerRepository;
import org.yassir.itlens.service.ISubSubject;

@Component
public class IdExistValidator implements ConstraintValidator<IdExist, Long> {

    private final OwnerRepository ownerRepository;
    private final ISurveyRepository surveyRepository;
    private final ISubSubject subSubject;
    private final ISurveyEditionRepository surveyEditionRepository;
    private final ISubjectRepository subjectRepository;

    @Autowired
    public IdExistValidator(OwnerRepository ownerRepository, ISurveyRepository surveyRepository , ISubSubject subSubject, ISurveyEditionRepository surveyEditionRepository, ISubjectRepository subjectRepository) {
        this.ownerRepository = ownerRepository;
        this.surveyRepository = surveyRepository;
        this.subSubject = subSubject;
        this.surveyEditionRepository = surveyEditionRepository;
        this.subjectRepository = subjectRepository;
    }



    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        System.out.println("Validating ID: " + id);
        if (id == null) {
            System.out.println("ID is null, returning false.");
            return false;
        }

        boolean ownerExists = ownerRepository.existsById(id);
        boolean surveyExists = surveyRepository.existsById(id);
        boolean surveyEditionExists = surveyEditionRepository.existsById(id);
        boolean subjectExists = subjectRepository.existsById(id);

        System.out.println("Validating ID in repositories:");
        System.out.println("Owner exists: " + ownerExists);
        System.out.println("Survey exists: " + surveyExists);
        System.out.println("SurveyEdition exists: " + surveyEditionExists);
        System.out.println("subject exists: " + subjectExists);



        return ownerExists || surveyExists || surveyEditionExists || subjectExists;
    }



}
