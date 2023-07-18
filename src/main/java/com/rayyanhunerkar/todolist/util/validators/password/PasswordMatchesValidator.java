package com.rayyanhunerkar.todolist.util.validators.password;

import com.rayyanhunerkar.todolist.POJO.Users.SignUpRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        SignUpRequest user = (SignUpRequest) o;
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
