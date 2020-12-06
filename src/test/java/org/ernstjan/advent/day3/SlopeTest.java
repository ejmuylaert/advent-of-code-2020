package org.ernstjan.advent.day3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SlopeTest {

    @Test
    void sampleValidation() {
        List<String> map = List.of(
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
        );
        Slope slope = new Slope(map);

        assertEquals(slope.treeEncounter(1, 1), 2);
        assertEquals(slope.treeEncounter(3, 1), 7);
        assertEquals(slope.treeEncounter(5, 1), 3);
        assertEquals(slope.treeEncounter(7, 1), 4);
        assertEquals(slope.treeEncounter(1, 2), 2);
    }

    @Test
    void sampleMultiplication() {
        List<String> map = List.of(
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
        );
        Slope slope = new Slope(map);

        assertEquals(slope.treeEncounterMultiplication(), 336);
    }
}
