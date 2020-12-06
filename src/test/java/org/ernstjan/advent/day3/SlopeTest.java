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

        assertEquals(slope.treeEncounter(3, 1), 7);
    }
}
