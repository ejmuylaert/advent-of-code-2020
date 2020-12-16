package org.ernstjan.advent.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdapterTest {

    @Test
    void sample() {
        List<String> lines = List.of(
                "28",
                "33",
                "18",
                "42",
                "31",
                "14",
                "46",
                "20",
                "48",
                "47",
                "24",
                "23",
                "49",
                "45",
                "19",
                "38",
                "39",
                "11",
                "1",
                "32",
                "25",
                "35",
                "8",
                "17",
                "7",
                "9",
                "4",
                "2",
                "34",
                "10",
                "3");

        assertEquals(Adapter.calculate(lines), 220);
    }

    @Test
    void simpleSampleCombinators() {
        List<String> lines = List.of("16",
                "10",
                "15",
                "5",
                "1",
                "11",
                "7",
                "19",
                "6",
                "12",
                "4");

        assertEquals(Adapter.combinations(lines), 8);

    }

    @Test
    void sampleCombinations() {
        List<String> lines = List.of(
                "28",
                "33",
                "18",
                "42",
                "31",
                "14",
                "46",
                "20",
                "48",
                "47",
                "24",
                "23",
                "49",
                "45",
                "19",
                "38",
                "39",
                "11",
                "1",
                "32",
                "25",
                "35",
                "8",
                "17",
                "7",
                "9",
                "4",
                "2",
                "34",
                "10",
                "3");

        assertEquals(Adapter.combinations(lines), 19208);
    }
}
