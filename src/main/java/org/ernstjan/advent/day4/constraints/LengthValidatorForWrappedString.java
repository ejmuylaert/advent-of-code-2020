package org.ernstjan.advent.day4.constraints;

import org.ernstjan.advent.day4.PassportTypes;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.constraintvalidators.hv.LengthValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LengthValidatorForWrappedString implements ConstraintValidator<Length, PassportTypes.WrappedValue<String>> {
    private final LengthValidator lengthValidator = new LengthValidator();

    @Override
    public void initialize(Length constraintAnnotation) {
        lengthValidator.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PassportTypes.WrappedValue<String> value, ConstraintValidatorContext constraintValidatorContext) {
        return lengthValidator.isValid(value.getValue(), constraintValidatorContext);
    }
}
