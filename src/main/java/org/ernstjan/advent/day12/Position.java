package org.ernstjan.advent.day12;

import java.util.Objects;

public class Position {

    private final int x;
    private final int y;
    private final Direction facing;

    public Position(int x, int y, Direction facing) {
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    public Position translate(int dx, int dy) {
        return new Position(x + dx, y + dy, facing);
    }

    public Position face(Direction direction) {
        return new Position(x, y, direction);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getFacing() {
        return facing;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", facing=" + facing +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y && facing == position.facing;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, facing);
    }
}
