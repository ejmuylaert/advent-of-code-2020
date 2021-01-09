package org.ernstjan.advent.day12;

import java.util.Objects;

public class Waypoint {
    private final int x;
    private final int y;

    public Waypoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Waypoint translate(int dx, int dy) {
        return new Waypoint(x + dx, y + dy);
    }

    // https://calcworkshop.com/transformations/rotation-rules/
    public Waypoint rotate90degreesClockwise() {
        return new Waypoint(y, -1 * x);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Waypoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Waypoint waypoint = (Waypoint) o;
        return x == waypoint.x && y == waypoint.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
