package org.ernstjan.advent.day12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.ernstjan.advent.day12.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class InstructionTest {

    @ParameterizedTest
    @MethodSource("movePositiveValues")
    @DisplayName("Go north increases the y axis")
    void northTest(int originY, int movement, int resultY) {
        Position origin = new Position(0, originY, SOUTH);
        Instructions.MoveAbsolute instruction = new Instructions.MoveAbsolute(NORTH, movement);

        assertEquals(resultY, instruction.move(origin).getY());
    }

    @ParameterizedTest
    @MethodSource("movePositiveValues")
    @DisplayName("Go east increases the x axis")
    void eastTest(int originX, int movement, int resultX) {
        Position origin = new Position(originX, 0, WEST);
        Instructions.MoveAbsolute instruction = new Instructions.MoveAbsolute(EAST, movement);

        assertEquals(resultX, instruction.move(origin).getX());
    }

    @ParameterizedTest
    @MethodSource("moveNegativeValues")
    @DisplayName("Go south decreases the y axis")
    void southTest(int originY, int movement, int resultY) {
        Position origin = new Position(0, originY, SOUTH);
        Instructions.MoveAbsolute instruction = new Instructions.MoveAbsolute(SOUTH, movement);

        assertEquals(resultY, instruction.move(origin).getY());
    }

    @ParameterizedTest
    @MethodSource("moveNegativeValues")
    @DisplayName("Go west decreases the x axis")
    void westTest(int originX, int movement, int resultX) {
        Position origin = new Position(originX, 0, NORTH);
        Instructions.MoveAbsolute instruction = new Instructions.MoveAbsolute(WEST, movement);

        assertEquals(resultX, instruction.move(origin).getX());
    }

    @ParameterizedTest
    @MethodSource("moveForwardValues")
    @DisplayName("Move forward moves into the current direction")
    void forwardTest(Direction currentDirection, int movement, int resultX, int resultY) {
        Position origin = new Position(0, 0, currentDirection);
        Instructions.MoveForward instruction = new Instructions.MoveForward(movement);

        Position result = new Position(resultX, resultY, currentDirection);
        assertEquals(result, instruction.move(origin));
    }

    @ParameterizedTest
    @MethodSource("turnRightValues")
    @DisplayName("Turn right changes the direction")
    void turnRightTest(Direction originDirection, int degrees, Direction resultDirection) {
        Position origin = new Position(0, 0, originDirection);
        Instructions.TurnRight instruction = new Instructions.TurnRight(degrees);

        assertEquals(resultDirection, instruction.move(origin).getFacing());
    }

    @ParameterizedTest
    @MethodSource("turnLeftValues")
    @DisplayName("Turn left changes the direction")
    void turnLeftTest(Direction originDirection, int degrees, Direction resultDirection) {
        Position origin = new Position(0, 0, originDirection);
        Instructions.TurnLeft instruction = new Instructions.TurnLeft(degrees);

        assertEquals(resultDirection, instruction.move(origin).getFacing());
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

    static Stream<Arguments> moveForwardValues() {
        // current direction, movement, result x, result y
        return Stream.of(
                arguments(NORTH, 20, 0, 20),
                arguments(EAST, 5, 5, 0),
                arguments(SOUTH, 7, 0, -7),
                arguments(WEST, 10, -10, 0));
    }

    static Stream<Arguments> turnRightValues() {
        // current direction, degrees, result direction
        return Stream.of(
                arguments(NORTH, 90, EAST),
                arguments(NORTH, 180, SOUTH),
                arguments(NORTH, 270, WEST),
                arguments(NORTH, 360, NORTH),
                arguments(EAST, 90, SOUTH),
                arguments(SOUTH, 180, NORTH));
    }

    static Stream<Arguments> turnLeftValues() {
        // current direction, degrees, result direction
        return Stream.of(
                arguments(NORTH, 90, WEST),
                arguments(NORTH, 180, SOUTH),
                arguments(NORTH, 270, EAST),
                arguments(NORTH, 360, NORTH),
                arguments(EAST, 90, NORTH),
                arguments(SOUTH, 180, NORTH));
    }

    static Stream<Arguments> rotateRightValues() {
        // current x, y, degrees, result x, y
        return Stream.of(
                arguments(4, 10, 90, -10, 4)
        );
    }
}
