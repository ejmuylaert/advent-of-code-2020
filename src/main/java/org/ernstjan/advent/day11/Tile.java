package org.ernstjan.advent.day11;

import java.util.Map;

public enum Tile {
    EMPTY("L"),
    TAKEN("#"),
    FLOOR(".");

    private final String name;

    Tile(String s) {
        name = s;
    }

    private static final Map<Character, Tile> nameToValueMap =
            Map.of('L', EMPTY, '#', TAKEN, '.', FLOOR);

    static Tile forName(Character name) {
        return nameToValueMap.get(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
