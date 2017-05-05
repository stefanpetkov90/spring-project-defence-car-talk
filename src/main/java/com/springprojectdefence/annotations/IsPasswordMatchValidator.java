package com.springprojectdefence.annotations;

import com.springprojectdefence.entities.User;
import com.springprojectdefence.models.bindingModels.RegistrationModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by stefa on 3/26/2017.
 */
public class IsPasswordMatchValidator implements ConstraintValidator<IsPasswordMatch, Object> {
    @Override
    public void initialize(IsPasswordMatch isPasswordMatch) {

    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        if (userClass instanceof RegistrationModel){
            return ((RegistrationModel) userClass).getPassword().equals(((RegistrationModel) userClass).getConfirmedPassword());
        }
        return false;
    }
}
