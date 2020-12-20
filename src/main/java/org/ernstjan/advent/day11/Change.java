package org.ernstjan.advent.day11;

public class Change {
    private final Coordinate coordinate;
    private final Tile newValue;

    public Change(Coordinate coordinate, Tile newValue) {
        this.coordinate = coordinate;
        this.newValue = newValue;
    }

    public Tile getNewValue() {
        return newValue;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "Change{" +
                "coordinate=" + coordinate +
                ", newValue=" + newValue +
                '}';
    }
}
