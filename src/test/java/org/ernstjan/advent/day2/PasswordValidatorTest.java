package org.ernstjan.advent.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {

    @Test
    void sampleValidations() {
        assertTrue(PasswordValidator.isValid("1-3 a: abcde"));
        assertFalse(PasswordValidator.isValid("1-3 b: cdefg"));
        assertTrue(PasswordValidator.isValid("2-9 c: ccccccccc"));
    }
}
