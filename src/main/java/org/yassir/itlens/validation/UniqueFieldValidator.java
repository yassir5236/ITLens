package org.yassir.itlens.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yassir.itlens.repository.OwnerRepository;
import org.yassir.itlens.repository.ISurveyRepository;

@Component

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, String> {

    private final OwnerRepository ownerRepository;
    private final ISurveyRepository surveyRepository;



    @Autowired
    public UniqueFieldValidator(OwnerRepository ownerRepository, ISurveyRepository surveyRepository) {
        this.ownerRepository = ownerRepository;
        this.surveyRepository = surveyRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        if (ownerRepository.findFirstByName(value).isPresent()) {
            return false;
        }

        return surveyRepository.findFirstByTitle(value).isEmpty();
    }
}

