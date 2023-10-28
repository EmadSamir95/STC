package com.stc.systemdesign.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TypeValidator.class)
public @interface ITypeValidator {
    String message() default "Invalid type provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
