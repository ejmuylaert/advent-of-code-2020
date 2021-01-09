package org.ernstjan.advent.day12;

import java.util.Map;

public enum Direction {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    private final int value;

    Direction(int value) {
        this.value = value;
    }

    private static final Map<Integer, Direction> numberToValueMap =
            Map.of(0, NORTH, 1, EAST, 2, SOUTH, 3, WEST);

    static Direction forInt(Integer number) {
        return numberToValueMap.get(number);
    }

    public int getValue() {
        return value;
    }
}
