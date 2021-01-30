package org.ernstjan.advent.day4.constraints;

import org.ernstjan.advent.day4.PassportTypes;
import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.MaxValidatorForInteger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Max;

public class MaxValidatorForWrappedInteger implements ConstraintValidator<Max, PassportTypes.WrappedValue<Integer>> {
    private final MaxValidatorForInteger integerValidator = new MaxValidatorForInteger();

    @Override
    public void initialize(Max constraintAnnotation) {
        integerValidator.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PassportTypes.WrappedValue<Integer> wrappedValue, ConstraintValidatorContext constraintValidatorContext) {
        return integerValidator.isValid(wrappedValue.getValue(), constraintValidatorContext);
    }
}
