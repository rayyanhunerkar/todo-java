package com.rayyanhunerkar.todolist.util.validators.jwt;

import com.rayyanhunerkar.todolist.util.validators.email.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({TYPE, METHOD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = JWTValidator.class)
@Documented
public @interface JWTmatcher {
    String message() default "Invalid token";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
