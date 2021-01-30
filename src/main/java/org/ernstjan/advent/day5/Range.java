package org.ernstjan.advent.day5;

class Range {
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
