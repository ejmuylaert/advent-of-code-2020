package org.ernstjan.advent.day6;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomDeclarationTest {

    @Test
    void sampleDeclaration() {
        List<String> lines = List.of(
                "abc",
                "",
                "a",
                "b",
                "c",
                "",
                "ab",
                "ac",
                "",
                "a",
                "a",
                "a",
                "a",
                "",
                "b"
        );

        assertEquals(new CustomsDeclaration().declare(lines), 11);
    }

    @Test
    void sampleSecondDeclaration() {
        List<String> lines = List.of(
                "abc",
                "",
                "a",
                "b",
                "c",
                "",
                "ab",
                "ac",
                "",
                "a",
                "a",
                "a",
                "a",
                "",
                "b"
        );

        assertEquals(new CustomsDeclaration().declareCommon(lines), 6);

    }
}
