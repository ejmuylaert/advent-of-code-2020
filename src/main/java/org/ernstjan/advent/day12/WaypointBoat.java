package org.ernstjan.advent.day12;

import java.util.List;

import org.ernstjan.advent.day12.WaypointInstructions.*;

public class WaypointBoat {

    private Waypoint waypoint = new Waypoint(10, 1);
    private Waypoint position = new Waypoint(0, 0);

    public void move(List<String> instructions) {
        for (String instruction : instructions) {
            String commando = instruction.substring(0, 1);
            int argument = Integer.parseInt(instruction.substring(1));

            switch (commando) {
                case "L":
                    this.waypoint = new RotateLeft(argument).move(waypoint);
                    break;
                case "R":
                    this.waypoint = new RotateRight(argument).move(waypoint);
                    break;

                case "N":
                    this.waypoint = new MoveAbsolute(Direction.NORTH, argument).move(waypoint);
                    break;
                case "E":
                    this.waypoint = new MoveAbsolute(Direction.EAST, argument).move(waypoint);
                    break;
                case "S":
                    this.waypoint = new MoveAbsolute(Direction.SOUTH, argument).move(waypoint);
                    break;
                case "W":
                    this.waypoint = new MoveAbsolute(Direction.WEST, argument).move(waypoint);
                    break;

                case "F":
                    this.position = this.position.translate(
                            waypoint.getX() * argument,
                            waypoint.getY() * argument);
                    break;

                default:
                    throw new RuntimeException("Unknown commando: " + commando);
            }
        }
    }

    public int manhattanDistance() {
        return Math.abs(position.getX()) + Math.abs(position.getY());
    }
}
