package org.ernstjan.advent.day7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void connectNode() {
        Graph graph = new Graph();

        graph.add("light red bag");

        graph.add("bright white bag");
        graph.connect("light red bag", "bright white bag", 1);
        graph.add("muted yellow bag");
        graph.connect("light red bag", "muted yellow bag", 2);

        Node node = graph.get("muted yellow bag");
        assertEquals(node.getParent().getName(), "light red bag");
    }

    @Test
    void connectMultipleParentNodes() {
        Graph graph = new Graph();
        // shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        graph.add("shiny gold bag");

        graph.add("dark olive bag");
        graph.connect("shiny gold bag", "dark olive bag", 1);
        graph.add("vibrant plum bag");
        graph.connect("shiny gold bag", "vibrant plum bag", 2);


        // dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        graph.add("dark olive bag");

        graph.add("faded blue bag");
        graph.connect("dark olive bag", "faded blue bag", 3);
        graph.add("dotted black bag");
        graph.connect("dark olive bag", "dotted black bag", 4);


        Node goldBag = graph.get("shiny gold bag");
        Node oliveBag = goldBag.getChildren().get("dark olive bag");
        assertEquals(oliveBag.getChildren().size(), 2);
    }

    @Test
    void containsChildNode() {
        Graph graph = new Graph();
        // shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        graph.add("shiny gold bag");

        graph.add("dark olive bag");
        graph.connect("shiny gold bag", "dark olive bag", 1);
        graph.add("vibrant plum bag");
        graph.connect("shiny gold bag", "vibrant plum bag", 2);


        // dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        graph.add("dark olive bag");

        graph.add("faded blue bag");
        graph.connect("dark olive bag", "faded blue bag", 3);
        graph.add("dotted black bag");
        graph.connect("dark olive bag", "dotted black bag", 4);


        Node oliveBag = graph.get("dark olive bag");
        Node goldBag = graph.get("shiny gold bag");

        assertTrue(oliveBag.contains("dotted black bag"));
        assertTrue(goldBag.contains("dotted black bag"));

        assertTrue(goldBag.contains("vibrant plum bag"));
        assertFalse(oliveBag.contains("vibrant plum bag"));
    }

    @Test
    void hardcodedSample() {
        Graph graph = new Graph();

//        light red bags contain 1 bright white bag, 2 muted yellow bags.
        graph.add("light red bag");

        graph.add("bright white bag");
        graph.connect("light red bag", "bright white bag", 1);
        graph.add("muted yellow bag");
        graph.connect("light red bag", "muted yellow bag", 2);
//        dark orange bags contain 3 bright white bags, 4 muted yellow bags.
        graph.add("dark orange bag");

        graph.add("bright white bag");
        graph.connect("dark orange bag", "bright white bag", 3);
        graph.add("muted yellow bag");
        graph.connect("dark orange bag", "muted yellow bag", 4);
//        bright white bags contain 1 shiny gold bag.
        graph.add("bright white bag");
        graph.add("shiny gold bag");
        graph.connect("bright white bag", "shiny gold bag", 1);
//        muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
        graph.add("muted yellow bag");
        graph.add("shiny gold bag");
        graph.connect("muted yellow bag", "shiny gold bag", 2);
        graph.add("faded blue bag");
        graph.connect("muted yellow bag", "faded blue bag", 9);
//        shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        graph.add("shiny gold bag");
        graph.add("dark olive bag");
        graph.connect("shiny gold bag", "dark olive bag", 1);
        graph.add("vibrant plum bag");
        graph.connect("shiny gold bag", "vibrant plum bag", 2);
//        dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        graph.add("dark olive bag");
        graph.add("faded blue bag");
        graph.connect("dark olive bag", "faded blue bag", 3);
        graph.add("dotted black bag");
        graph.connect("dark olive bag", "dotted black bag", 4);
//        vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
        graph.add("vibrant plum bag");
        graph.add("faded blue bag");
        graph.connect("vibrant plum bag", "faded blue bag", 5);
        graph.add("dotted black bag");
        graph.connect("vibrant plum bag", "dotted black bag", 6);
//        faded blue bags contain no other bags.
        graph.add("faded blue bag");
//        dotted black bags contain no other bags.
        graph.add("dotted black bag");

        assertTrue(graph.get("light red bag").contains("shiny gold bag"));
        assertEquals(graph.possibilities("shiny gold bag"), 4);
    }

    @Test
    void readSample() {
        List<String> lines = List.of(
                "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                "bright white bags contain 1 shiny gold bag.",
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
                "faded blue bags contain no other bags.",
                "dotted black bags contain no other bags.");

        Graph graph = new Graph();
        graph.read(lines);

        assertTrue(graph.get("light red bag").contains("shiny gold bag"));
        assertEquals(graph.possibilities("shiny gold bag"), 4);
    }

    @Test
    void containingBags() {
        List<String> lines = List.of(
                "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                "bright white bags contain 1 shiny gold bag.",
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
                "faded blue bags contain no other bags.",
                "dotted black bags contain no other bags.");

        Graph graph = new Graph();
        graph.read(lines);

        assertEquals(graph.contains("shiny gold bag"), 32);
    }

    @Test
    void containingNextSample() {
        List<String> lines = List.of(
                "shiny gold bags contain 2 dark red bags.",
                "dark red bags contain 2 dark orange bags.",
                "dark orange bags contain 2 dark yellow bags.",
                "dark yellow bags contain 2 dark green bags.",
                "dark green bags contain 2 dark blue bags.",
                "dark blue bags contain 2 dark violet bags.",
                "dark violet bags contain no other bags."
        );

        Graph graph = new Graph();
        graph.read(lines);

        assertEquals(graph.contains("shiny gold bag"), 126);
    }
}
