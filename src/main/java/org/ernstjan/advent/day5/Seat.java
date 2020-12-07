package org.ernstjan.advent.day5;

import java.util.Objects;
import java.util.Optional;

public class Seat {

    private final int row;
    private final int column;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    private static class Range {
        private final int low;
        private final int high;
        private final Optional<Integer> value;

        Range(int low, int high) {
            this.low = low;
            this.high = high;
            this.value = Optional.empty();
        }

        Range(int low, int high, int value) {
            this.low = low;
            this.high = high;
            this.value = Optional.of(value);
        }

        Range withValue(int value) {
            return new Range(this.low, this.high, value);
        }

        Range lowerHalf() {
            int half = (high - low) / 2;
            Range range = new Range(low, low + half);
            if (range.high - range.low <= 1) {
                return range.withValue(range.low);
            } else {
                return range;
            }
        }

        Range upperHalf() {
            int half = (high - low) / 2;
            Range range = new Range(high - half, high);
            if (range.high - range.low <= 1) {
                return range.withValue(range.high);
            } else {
                return range;
            }
        }

        int value() {
            return value.orElseThrow();
        }

        @Override
        public String toString() {
            return "Range{" +
                    "low=" + low +
                    ", high=" + high +
                    ", value=" + value +
                    '}';
        }
    }

    public Seat(String code) {
        Range range = new Range(0, 127);
        for (char next_code : code.substring(0, 7).toCharArray()) {
            if (next_code == 'F') {
                range = range.lowerHalf();
            } else {
                range = range.upperHalf();
            }
        }
        row = range.value();

        range = new Range(0, 7);
        for (char next_code : code.substring(7).toCharArray()) {
            if (next_code == 'L') {
                range = range.lowerHalf();
            } else {
                range = range.upperHalf();
            }
        }
        column = range.value();
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
        Seat seat = (Seat) o;
        return row == seat.row &&
                column == seat.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
