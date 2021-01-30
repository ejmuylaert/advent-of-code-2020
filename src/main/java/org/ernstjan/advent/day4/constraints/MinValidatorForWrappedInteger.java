package org.ernstjan.advent.day4.constraints;

import org.ernstjan.advent.day4.PassportTypes;
import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.MinValidatorForInteger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Min;

public class MinValidatorForWrappedInteger implements ConstraintValidator<Min, PassportTypes.WrappedValue<Integer>> {
    private final MinValidatorForInteger integerValidator = new MinValidatorForInteger();

    @Override
    public void initialize(Min constraintAnnotation) {
        integerValidator.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PassportTypes.WrappedValue<Integer> wrappedValue, ConstraintValidatorContext constraintValidatorContext) {
        return integerValidator.isValid(wrappedValue.getValue(), constraintValidatorContext);
    }
}