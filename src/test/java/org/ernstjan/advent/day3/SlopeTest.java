package org.ernstjan.advent.day3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SlopeTest {

    private final Slope sampleSlope = new Slope(List.of(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#"
    ));

    @Test
    @DisplayName("Check if tree is at position")
    void checkTree() {
        assertThat(sampleSlope.hasTreeAt(new Position(0, 0))).contains(false);
        assertThat(sampleSlope.hasTreeAt(new Position(2, 0))).contains(true);
        assertThat(sampleSlope.hasTreeAt(new Position(0, 1))).contains(true);
    }

    @Test
    @DisplayName("Check tree in repeating part of map")
    void checkTreeInRepeatedPart() {
        assertThat(sampleSlope.hasTreeAt(new Position(11, 0))).contains(false);
        assertThat(sampleSlope.hasTreeAt(new Position(13, 0))).contains(true);
        assertThat(sampleSlope.hasTreeAt(new Position(14, 0))).contains(true);
    }

    @Test
    @DisplayName("Return none, when query tree below slope")
    void returnNoneWhenBelowSlope() {
        assertThat(sampleSlope.hasTreeAt(new Position(11, 11))).isEmpty();
        assertThat(sampleSlope.hasTreeAt(new Position(2, 12))).isEmpty();
    }

    @Test
    @DisplayName("Throw error when try to query tree above slope (negative y)")
    void throwErrorOnInvalidY() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> sampleSlope.hasTreeAt(new Position(10, -20)));
    }

    @Test
    @DisplayName("Negative x, should check at the left of the slope")
    void handleNegativeX() {
        assertThat(sampleSlope.hasTreeAt(new Position(-8, 0))).contains(true);
        assertThat(sampleSlope.hasTreeAt(new Position(-19, 0))).contains(true);
        assertThat(sampleSlope.hasTreeAt(new Position(-20, 0))).contains(true);
        assertThat(sampleSlope.hasTreeAt(new Position(-21, 0))).contains(false);
    }

    @Test
    @DisplayName("Map should have at least 1 line")
    void checkAtLeastOneLine() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Slope(List.of()));
    }

    @Test
    @DisplayName("All lines should be of equal length")
    void checkEqualLineLengths() {
        List<String> map = List.of(
                "..##.......",
                "#...#...#..##",
                ".#....#..#.");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Slope(map));
    }

    @Test
    @DisplayName("Throw error when encountering unrecognized character on map")
    void throwErrorWhenMapNotValid() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Slope(List.of("..#..**..")));
    }

    @Test
    @DisplayName("Count number of trees during travel")
    void countNumberOfTrees() {
        assertEquals(sampleSlope.treeEncounter(new Delta(1, 1)), 2);
        assertEquals(sampleSlope.treeEncounter(new Delta(3, 1)), 7);
        assertEquals(sampleSlope.treeEncounter(new Delta(5, 1)), 3);
        assertEquals(sampleSlope.treeEncounter(new Delta(7, 1)), 4);
        assertEquals(sampleSlope.treeEncounter(new Delta(1, 2)), 2);
    }

    @Test
    @DisplayName("Calculate product of encountered trees during multiple travels")
    void calculateProductOfEncounteredTrees() {
        List<Delta> travels = List.of(new Delta(1, 1),
                new Delta(3, 1),
                new Delta(5, 1),
                new Delta(7, 1),
                new Delta(1, 2));
        assertThat(sampleSlope.treeEncountersProduct(travels)).isEqualTo(336);
    }
}
