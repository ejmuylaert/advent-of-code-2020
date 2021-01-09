package org.ernstjan.advent.day12;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoatTest {

    @Test
    void sample() {
        List<String> lines = List.of(
                "F10",
                "N3",
                "F7",
                "R90",
                "F11");

        Boat boat = new Boat();
        boat.move(lines);
        assertEquals(25, boat.manhattanDistance());
    }

    @Test
    void sampleWaypoint() {
        List<String> lines = List.of(
                "F10",
                "N3",
                "F7",
                "R90",
                "F11");

        WaypointBoat boat = new WaypointBoat();
        boat.move(lines);
        assertEquals(286, boat.manhattanDistance());
    }
}
