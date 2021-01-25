package org.ernstjan.advent.day2;

import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {
    @Test
    void sampleRental() {
        assertTrue(new PasswordLine(1, 3, 'a', "abcde").isValidRental());
        assertFalse(new PasswordLine(1, 3, 'b', "cdefg").isValidRental());
        assertTrue(new PasswordLine(2, 9, 'c', "ccccccccc").isValidRental());
    }

    @Test
    void sampleToboggan() {
        assertTrue(new PasswordLine(1, 3, 'a', "abcde").isValidToboggan());
        assertFalse(new PasswordLine(1, 3, 'b', "cdefg").isValidToboggan());
        assertFalse(new PasswordLine(2, 9, 'c', "ccccccccc").isValidToboggan());
    }

    @Test
    void countValidPasswords() {
        String sample = "1-3 a: abcde\n" +
                "1-3 b: cdefg\n" +
                "2-9 c: ccccccccc";

        List<PasswordLine> lines = PasswordFileParser.parse(CharStreams.fromString(sample));

        assertThat(PasswordLine.countValid(lines, PasswordLine::isValidRental)).isEqualTo(2);
        assertThat(PasswordLine.countValid(lines, PasswordLine::isValidToboggan)).isEqualTo(1);
    }
}
