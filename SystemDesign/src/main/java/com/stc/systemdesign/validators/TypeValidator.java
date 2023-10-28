package com.stc.systemdesign.validators;

import com.stc.systemdesign.entity.Type;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class TypeValidator implements ConstraintValidator<ITypeValidator, String> {

    List<Type> types;

    @Override
    public void initialize(ITypeValidator constraintAnnotation) {
        types = Arrays.stream(Type.values()).toList();
    }

    @Override
    public boolean isValid(String type, ConstraintValidatorContext constraintValidatorContext) {
        return types.contains(Type.valueOf(type));
    }
}
