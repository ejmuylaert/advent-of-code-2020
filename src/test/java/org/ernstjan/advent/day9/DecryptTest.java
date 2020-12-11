package org.ernstjan.advent.day9;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecryptTest {

    @Test
    void sample() {
        List<Long> input = List.of(
                35L,
                20L,
                15L,
                25L,
                47L,
                40L,
                62L,
                55L,
                65L,
                95L,
                102L,
                117L,
                150L,
                182L,
                127L,
                219L,
                299L,
                277L,
                309L,
                576L);

        Decrypt decrypt = new Decrypt(input, 5);
        assertEquals(decrypt.firstError(), 127);
    }

    @Test
    void textSample() {
        List<String> lines = List.of(
                "35",
                "20",
                "15",
                "25",
                "47",
                "40",
                "62",
                "55",
                "65",
                "95",
                "102",
                "117",
                "150",
                "182",
                "127",
                "219",
                "299",
                "277",
                "309",
                "576");

        Decrypt decrypt = Decrypt.fromLines(lines, 5);
        assertEquals(decrypt.firstError(), 127);
    }

    @Test
    void sampleWeakness() {
        List<Long> input = List.of(
                35L,
                20L,
                15L,
                25L,
                47L,
                40L,
                62L,
                55L,
                65L,
                95L,
                102L,
                117L,
                150L,
                182L,
                127L,
                219L,
                299L,
                277L,
                309L,
                576L);

        Decrypt decrypt = new Decrypt(input, 5);
        assertEquals(decrypt.weakness(), 62);
    }
}
