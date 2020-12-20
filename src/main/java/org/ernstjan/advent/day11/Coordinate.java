package org.ernstjan.advent.day11;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate neighbour(Direction direction) {
        switch (direction) {
            case NORTH:
                return new Coordinate(this.getX(), this.getY() - 1);
            case NORTH_EAST:
                return new Coordinate(this.getX() + 1, this.getY() - 1);
            case EAST:
                return new Coordinate(this.getX() + 1, this.getY());
            case SOUTH_EAST:
                return new Coordinate(this.getX() + 1, this.getY() + 1);
            case SOUTH:
                return new Coordinate(this.getX(), this.getY() + 1);
            case SOUTH_WEST:
                return new Coordinate(this.getX() - 1, this.getY() + 1);
            case WEST:
                return new Coordinate(this.getX() - 1, this.getY());
            case NORTH_WEST:
                return new Coordinate(this.getX() - 1, this.getY() - 1);

            default:
                throw new RuntimeException("Missing direction case");
        }
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
