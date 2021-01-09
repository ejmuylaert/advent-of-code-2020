package org.ernstjan.advent.day12;

public class Instructions {

    public static Instruction fromSting(String instruction) {
        String commando = instruction.substring(0, 1);
        int argument = Integer.parseInt(instruction.substring(1));

        switch (commando) {
            case "L":
                return new TurnLeft(argument);
            case "R":
                return new TurnRight(argument);

            case "N":
                return new MoveAbsolute(Direction.NORTH, argument);
            case "E":
                return new MoveAbsolute(Direction.EAST, argument);
            case "S":
                return new MoveAbsolute(Direction.SOUTH, argument);
            case "W":
                return new MoveAbsolute(Direction.WEST, argument);

            case "F":
                return new MoveForward(argument);

            default:
                throw new RuntimeException("Unknown commando: " + commando);
        }
    }

    public interface Instruction {
        Position move(Position origin);
    }

    public static class MoveAbsolute implements Instruction {
        private final Direction direction;
        private final int value;

        public MoveAbsolute(Direction direction, int value) {
            this.direction = direction;
            this.value = value;
        }

        public Position move(Position origin) {
            switch (direction) {
                case NORTH:
                    return origin.translate(0, value);
                case EAST:
                    return origin.translate(value, 0);
                case SOUTH:
                    return origin.translate(0, -1 * value);
                case WEST:
                    return origin.translate(-1 * value, 0);
                default:
                    throw new RuntimeException("Unknown direction: " + direction);
            }
        }
    }

    public static class MoveForward implements Instruction {
        private final int value;

        public MoveForward(int value) {
            this.value = value;
        }

        public Position move(Position origin) {
            MoveAbsolute absoluteInstruction = new MoveAbsolute(origin.getFacing(), value);
            return absoluteInstruction.move(origin);
        }
    }

    public static class TurnRight implements Instruction {
        private final int value;

        public TurnRight(int value) {
            this.value = value;
        }

        public Position move(Position origin) {
            int directionValue = (origin.getFacing().getValue() + (value / 90)) % 4;

            return origin.face(Direction.forInt(directionValue));
        }
    }

    public static class TurnLeft implements Instruction {
        private final int value;

        public TurnLeft(int value) {
            this.value = value;
        }

        public Position move(Position origin) {
            int directionValue = (origin.getFacing().getValue() + ((360 - value) / 90)) % 4;

            return origin.face(Direction.forInt(directionValue));
        }
    }
}
