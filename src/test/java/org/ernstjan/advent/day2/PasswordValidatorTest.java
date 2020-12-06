package org.ernstjan.advent.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {

    @Test
    void sampleValidations() {
        assertTrue(PasswordValidator.isValidRental("1-3 a: abcde"));
        assertFalse(PasswordValidator.isValidRental("1-3 b: cdefg"));
        assertTrue(PasswordValidator.isValidRental("2-9 c: ccccccccc"));
    }

    @Test
    void sampleToboggan() {
        assertTrue(PasswordValidator.isValidToboggan("1-3 a: abcde"));
        assertFalse(PasswordValidator.isValidToboggan("1-3 b: cdefg"));
        assertFalse(PasswordValidator.isValidToboggan("2-9 c: ccccccccc"));

    }
}
