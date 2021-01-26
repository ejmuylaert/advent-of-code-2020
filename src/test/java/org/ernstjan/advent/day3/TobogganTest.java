package org.ernstjan.advent.day3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TobogganTest {

    @Test
    @DisplayName("Starting position for Toboggan is (0, 0)")
    void checkStartingPosition() {
        Toboggan toboggan = new Toboggan();

        assertThat(toboggan.getPosition()).isEqualTo(new Position(0, 0));
    }

    @Test
    @DisplayName("Test movement of Toboggan")
    void testMovement() {
        Toboggan toboggan = new Toboggan();

        toboggan.move(new Delta(1, 1));
        assertThat(toboggan.getPosition()).isEqualTo(new Position(1, 1));

        toboggan.move(new Delta(20, 3));
        assertThat(toboggan.getPosition()).isEqualTo(new Position(21, 4));

        toboggan.move(new Delta(-4, 0));
        assertThat(toboggan.getPosition()).isEqualTo(new Position(17, 4));
    }
}
