package org.ernstjan.advent.day5;

import java.util.Objects;

public class BoardingPass {

    private final int row;
    private final int column;


    public BoardingPass(String code) {
        row = partition(code.substring(0, 7), 127);
        column = partition(code.substring(7), 7);
    }

    private int partition(String code, int upperBound) {
        Range range = new Range(0, upperBound);
        for (char next_code : code.toCharArray()) {
            switch (next_code) {
                case 'F':
                case 'L':
                    range = range.lowerHalf();
                    break;
                case 'B':
                case 'R':
                    range = range.upperHalf();
                    break;
                default:
                    throw new IllegalArgumentException("Unrecognized code: " + next_code);
            }
        }

        return range.value();
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public int id() {
        return 8 * row + column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardingPass boardingPass = (BoardingPass) o;
        return row == boardingPass.row &&
                column == boardingPass.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    private static class Range {
        private final int low;
        private final int high;

        Range(int low, int high) {
            this.low = low;
            this.high = high;
        }

        Range lowerHalf() {
            int half = (high - low) / 2;
            return new Range(low, low + half);
        }

        Range upperHalf() {
            int half = (high - low) / 2;
            return new Range(high - half, high);
        }

        int value() {
            if (low != high) throw new IllegalStateException("Value is only valid when low & high are equal");

            return low;
        }
    }
}
