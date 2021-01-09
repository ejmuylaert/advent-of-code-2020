package org.ernstjan.advent.day13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Departure {

    public static int answer(List<String> lines) {
        int currentTime = Integer.parseInt(lines.get(0));
        BusDeparture fastest = Arrays.stream(lines.get(1).split(","))
                .filter(v -> !v.equals("x"))
                .map(Integer::parseInt)
                .map(lineNumber -> {
                    int waitingTime = next(currentTime, lineNumber) - currentTime;
                    return new BusDeparture(waitingTime, lineNumber);
                })
                .min(Comparator.comparingInt(o -> o.waitingTime))
                .orElseThrow();

        System.out.println(fastest);
        return fastest.multiplied();
    }

    public static long answer(String line) {
        String[] lineNumbers = line.split(",");
        List<BusOrder> busOrders = IntStream.range(0, lineNumbers.length)
                .boxed()
                .map(idx -> new Pair<>(idx, lineNumbers[idx]))
                .filter(pair -> !pair.right.equals("x"))
                .map(pair -> new Pair<>(pair.left, Integer.parseInt(pair.right)))
                .map(pair -> new BusOrder(pair.left, pair.right))
                .sorted(Comparator.comparingInt(b -> b.lineNumber))
                .collect(Collectors.toList());

        BusOrder highest = busOrders.get(busOrders.size() - 1);

        long time;
        for (time = -1 * highest.offset; ; time += highest.lineNumber) {

            final long currentTime = time;
            if (busOrders.stream().allMatch(o -> o.isValid(currentTime))) {
                break;
            }

            System.out.println(currentTime);
        }

        System.out.println("offset: " + highest.offset);
        System.out.println("number: " + highest.lineNumber);
        System.out.println("Is valid: " + highest.isValid(highest.offset + highest.lineNumber));

        long l = 100000000000000L;
        System.out.println(l);
        System.out.println(Long.MAX_VALUE);
        return time;
    }

    static int next(int currentTime, int lineNumber) {
        int minutesLeft = currentTime % lineNumber;
        int minutesUntilDeparture = lineNumber - minutesLeft;

        if (minutesLeft == 0) {
            return currentTime;
        } else {
            return currentTime + minutesUntilDeparture;
        }
    }

    private static class BusDeparture {
        private final int waitingTime;
        private final int lineNumber;

        public BusDeparture(int waitingTime, int lineNumber) {
            this.waitingTime = waitingTime;
            this.lineNumber = lineNumber;
        }

        int multiplied() {
            return waitingTime * lineNumber;
        }

        @Override
        public String toString() {
            return "BusDeparture{" +
                    "waitingTime=" + waitingTime +
                    ", lineNumber=" + lineNumber +
                    '}';
        }
    }

    private static class BusOrder {
        private final int offset;
        private final int lineNumber;

        public BusOrder(int offset, int lineNumber) {
            this.offset = offset;
            this.lineNumber = lineNumber;
        }

        boolean isValid(long currentTime) {
            return (currentTime + offset) % lineNumber == 0;
        }
    }

    private static class Pair<X, Y> {
        private final X left;
        private final Y right;

        public Pair(X left, Y right) {
            this.left = left;
            this.right = right;
        }

        public X getLeft() {
            return left;
        }

        public Y getRight() {
            return right;
        }
    }
}
