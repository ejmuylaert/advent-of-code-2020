package org.ernstjan.advent.day3;

import java.util.List;

public class Slope {

    private final List<String> map;

    private static class Position {
        private final int x;
        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Position move(int right, int down) {
            return new Position(x + right, y + down);
        }
    }

    public Slope(List<String> lines) {
        map = lines;
    }

    public int treeEncounter(int right, int down) {
        int slopeLength = map.size();
        int trees = 0;

        Position pos = new Position(0, 0);
        do {
            String line = map.get(pos.y);
            if (isTree(line, pos.x)) {
                trees++;
            }
            pos = pos.move(right, down);

        } while (slopeLength > pos.y);

        return trees;
    }

    private boolean isTree(String line, int x) {
        int length = line.length();
        x = x % length;
        return line.charAt(x) == '#';
    }
}
