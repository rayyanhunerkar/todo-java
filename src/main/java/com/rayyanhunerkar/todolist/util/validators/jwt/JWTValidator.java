package com.rayyanhunerkar.todolist.util.validators.jwt;

import com.rayyanhunerkar.todolist.model.User;
import com.rayyanhunerkar.todolist.util.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// TODO: Work on the Annotation for Validating JWT Tokens
public class JWTValidator implements ConstraintValidator<JWTmatcher, JwtValidationParams> {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public void initialize(JWTmatcher constraintAnnotation) {
    }

    @Override
    public boolean isValid(JwtValidationParams params, ConstraintValidatorContext constraintValidatorContext) {
        String token = params.token();
        User user = params.user();
        return jwtTokenUtil.ValidateJwtToken(token, user);
    }
}
