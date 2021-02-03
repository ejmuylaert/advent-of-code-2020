package org.ernstjan.advent.day7;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BagsTest {

    private final String sample = "light red bags contain 1 bright white bag, 2 muted yellow bags." +
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags." +
            "bright white bags contain 1 shiny gold bag." +
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags." +
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags." +
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags." +
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags." +
            "faded blue bags contain no other bags." +
            "dotted black bags contain no other bags.";

    @Test
    @DisplayName("Fill bags")
    void fillBags() {
        Bags bags = new Bags();

        bags.fillBag("light red", "bright white", 1);
        bags.fillBag("light red", "muted yellow", 2);

        assertThat(bags.numberOfContainingBags("light red")).isEqualTo(3);
    }

    @Test
    @DisplayName("Sample possibilities")
    void samplePossibilities() {
        CodePointCharStream stream = CharStreams.fromString(sample);
        Bags bags = Bags.fromCharStream(stream);

        assertThat(bags.numberOfPossibleOuterBags("shiny gold")).isEqualTo(4);
    }

    @Test
    @DisplayName("Containing bags")
    void sampleContainingBags() {
        CodePointCharStream stream = CharStreams.fromString(sample);
        Bags bags = Bags.fromCharStream(stream);

        assertThat(bags.numberOfContainingBags("shiny gold")).isEqualTo(32);
    }

    @Test
    @DisplayName("Other containing bags sample")
    void otherSample() {
        String sample = "shiny gold bags contain 2 dark red bags." +
                "dark red bags contain 2 dark orange bags." +
                "dark orange bags contain 2 dark yellow bags." +
                "dark yellow bags contain 2 dark green bags." +
                "dark green bags contain 2 dark blue bags." +
                "dark blue bags contain 2 dark violet bags." +
                "dark violet bags contain no other bags.";

        CodePointCharStream stream = CharStreams.fromString(sample);
        Bags bags = Bags.fromCharStream(stream);

        assertThat(bags.numberOfContainingBags("shiny gold")).isEqualTo(126);
    }
}
