package org.ernstjan.advent.day6;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomDeclarationTest {

    private static final String sample = "abc\n" +
            "\n" +
            "a\n" +
            "b\n" +
            "c\n" +
            "\n" +
            "ab\n" +
            "ac\n" +
            "\n" +
            "a\n" +
            "a\n" +
            "a\n" +
            "a\n" +
            "\n" +
            "b";

    private static CustomsDeclaration declaration = new CustomsDeclaration();

    @BeforeAll
    static void loadForms() {
        CodePointCharStream inputStream = CharStreams.fromString(sample);
        declaration.addForm(inputStream);
    }

    @Test
    void sampleDeclaration() {
        assertThat(declaration.declareAll()).isEqualTo(11);
    }

    @Test
    void sampleSecondDeclaration() {
        assertThat(declaration.declareCommon()).isEqualTo(6);
    }
}
