package org.ernstjan.advent.day3;

public class Toboggan {
    private Position position = new Position(0, 0);

    public void move(Delta delta) {
        position = new Position(position.getX() + delta.getRight(), position.getY() + delta.getDown());
    }

    public Position getPosition() {
        return position;
    }
}
