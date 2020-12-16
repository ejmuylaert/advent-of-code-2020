package org.ernstjan.advent.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComboGeneratorTest {

    @Test
    void oneNumberOneCombo() {
        assertEquals(1, ComboGenerator.generate(1));
    }

    @Test
    void twoNumbersOneCombo() {
        assertEquals(1, ComboGenerator.generate(2));
    }

    @Test
    void threeNumbersTwoCombos() {
        assertEquals(2, ComboGenerator.generate(3));
    }

    @Test
    void fourNumbers4Combos() {
        assertEquals(4, ComboGenerator.generate(4));
    }

    @Test
    void fiveNumbers22Combos() {
        assertEquals(7, ComboGenerator.generate(5));
    }
}
