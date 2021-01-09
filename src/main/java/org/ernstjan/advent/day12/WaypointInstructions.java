package org.ernstjan.advent.day12;

public class WaypointInstructions {

    public interface WaypointInstruction {
        Waypoint move(Waypoint origin);
    }

    public static class MoveAbsolute implements WaypointInstruction {
        private final Direction direction;
        private final int value;

        public MoveAbsolute(Direction direction, int value) {
            this.direction = direction;
            this.value = value;
        }

        public Waypoint move(Waypoint origin) {
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

    public static class RotateRight implements WaypointInstruction {
         private final int degrees;

        public RotateRight(int degrees) {
            this.degrees = degrees;
        }

        @Override
        public Waypoint move(Waypoint origin) {
            Waypoint current = origin;
            for (int i = 0; i < (degrees / 90); i++) {
                current = current.rotate90degreesClockwise();
            }
            return current;
        }
    }

    public static class RotateLeft implements WaypointInstruction {
        private final int degrees;

        public RotateLeft(int degrees) {
            this.degrees = degrees;
        }

        @Override
        public Waypoint move(Waypoint origin) {
            int steps = (360 - degrees) / 90;
            Waypoint current = origin;
            for (int i = 0; i < steps; i++) {
                current = current.rotate90degreesClockwise();
            }
            return current;
        }
    }
}
