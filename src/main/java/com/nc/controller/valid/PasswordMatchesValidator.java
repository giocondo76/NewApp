package com.nc.controller.valid;

import javax.validation.ConstraintValidator;

import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserRegistrationForm user = (UserRegistrationForm) obj;
        return user.getPassword().equals(user.getConfirmPassword());
    }
}