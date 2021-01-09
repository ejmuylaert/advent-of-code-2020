package org.ernstjan.advent.day12;

import java.util.List;

public class Boat {

    Position position = new Position(0, 0, Direction.EAST);

    public void move(List<String> instructions) {
        for (String instruction : instructions) {
            Instructions.Instruction instr = Instructions.fromSting(instruction);
            this.position = instr.move(position);
        }
    }

    public int manhattanDistance() {
        return Math.abs(position.getX()) + Math.abs(position.getY());
    }
}
