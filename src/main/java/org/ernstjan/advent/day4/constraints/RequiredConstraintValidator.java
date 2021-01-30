package org.ernstjan.advent.day4.constraints;

import org.ernstjan.advent.day4.PassportTypes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequiredConstraintValidator implements ConstraintValidator<Required, PassportTypes.WrappedValue<?>> {
    @Override
    public boolean isValid(PassportTypes.WrappedValue wrappedValue, ConstraintValidatorContext constraintValidatorContext) {
        if (wrappedValue == null) return false;

        return wrappedValue.getValue() != null;
    }
}
