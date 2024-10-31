
package org.yassir.itlens.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yassir.itlens.repository.ISurveyRepository;
import org.yassir.itlens.repository.OwnerRepository;

@Component
public class IdExistValidator implements ConstraintValidator<IdExist, Long> {

    private final OwnerRepository ownerRepository;
    private final ISurveyRepository surveyRepository;

    @Autowired
    public IdExistValidator(OwnerRepository ownerRepository, ISurveyRepository surveyRepository) {
        this.ownerRepository = ownerRepository;
        this.surveyRepository = surveyRepository;
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

        System.out.println("Owner exists: " + ownerExists + ", Survey exists: " + surveyExists);

        return ownerExists || surveyExists;
    }



}
