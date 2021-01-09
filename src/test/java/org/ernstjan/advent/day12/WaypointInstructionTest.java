package org.ernstjan.advent.day12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.ernstjan.advent.day12.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class WaypointInstructionTest {

    @ParameterizedTest
    @MethodSource("movePositiveValues")
    @DisplayName("Go north increases the y axis")
    void northTest(int originY, int movement, int resultY) {
        Waypoint origin = new Waypoint(0, originY);
        WaypointInstructions.MoveAbsolute instruction = new WaypointInstructions.MoveAbsolute(NORTH, movement);

        assertEquals(resultY, instruction.move(origin).getY());
    }

    @ParameterizedTest
    @MethodSource("movePositiveValues")
    @DisplayName("Go east increases the x axis")
    void eastTest(int originX, int movement, int resultX) {
        Waypoint origin = new Waypoint(originX, 0);
        WaypointInstructions.MoveAbsolute instruction = new WaypointInstructions.MoveAbsolute(EAST, movement);

        assertEquals(resultX, instruction.move(origin).getX());
    }

    @ParameterizedTest
    @MethodSource("moveNegativeValues")
    @DisplayName("Go south decreases the y axis")
    void southTest(int originY, int movement, int resultY) {
        Waypoint origin = new Waypoint(0, originY);
        WaypointInstructions.MoveAbsolute instruction = new WaypointInstructions.MoveAbsolute(SOUTH, movement);

        assertEquals(resultY, instruction.move(origin).getY());
    }

    @ParameterizedTest
    @MethodSource("moveNegativeValues")
    @DisplayName("Go west decreases the x axis")
    void westTest(int originX, int movement, int resultX) {
        Waypoint origin = new Waypoint(originX, 0);
        WaypointInstructions.MoveAbsolute instruction = new WaypointInstructions.MoveAbsolute(WEST, movement);

        assertEquals(resultX, instruction.move(origin).getX());
    }

    @ParameterizedTest
    @MethodSource("turnRightValues")
    @DisplayName("Turn right moves the waypoint around the origin")
    void turnTest(int inX, int inY, int degrees, int outX, int outY) {
        Waypoint origin = new Waypoint(inX, inY);
        WaypointInstructions.RotateRight instruction = new WaypointInstructions.RotateRight(degrees);

        assertEquals(new Waypoint(outX, outY), instruction.move(origin));
    }

    @ParameterizedTest
    @MethodSource("turnLeftValues")
    @DisplayName("Turn left moves the waypoint around the origin")
    void turnLeftTest(int inX, int inY, int degrees, int outX, int outY) {
        Waypoint origin = new Waypoint(inX, inY);
        WaypointInstructions.RotateLeft instruction = new WaypointInstructions.RotateLeft(degrees);

        assertEquals(new Waypoint(outX, outY), instruction.move(origin));
    }

    static Stream<Arguments> movePositiveValues() {
        // current y position, movement, result y position
        return Stream.of(
                arguments(0, 10, 10),
                arguments(10, 10, 20),
                arguments(-60, 4, -56),
                arguments(20, 0, 20));
    }

    static Stream<Arguments> moveNegativeValues() {
        // current y position, movement, result y position
        return Stream.of(
                arguments(0, 10, -10),
                arguments(10, 10, 0),
                arguments(-60, 4, -64),
                arguments(20, 0, 20));
    }

    static Stream<Arguments> turnRightValues() {
        return Stream.of(
                arguments(10, 4, 90, 4, -10),
                arguments(1, 2, 270, -2, 1),
                arguments(1, 2, 180, -1, -2),
                arguments(1, 2, 90, 2, -1),
                arguments(1, 2, 360, 1, 2));
    }

    static Stream<Arguments> turnLeftValues() {
        return Stream.of(
                arguments(1, 2, 90, -2, 1),
                arguments(1, 2, 180, -1, -2),
                arguments(1, 2, 270, 2, -1),
                arguments(1, 2, 360, 1, 2));
    }
}
