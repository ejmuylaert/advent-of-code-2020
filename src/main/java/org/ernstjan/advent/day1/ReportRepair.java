package org.ernstjan.advent.day1;

import java.util.Arrays;
import java.util.Optional;

public class ReportRepair {

    public static class Pair {
        int one;
        int two;

        Pair(int one, int two) {
            this.one = one;
            this.two = two;
        }

        public boolean isMatch() {
            return (one + two) == 2020;
        }

        public int product() {
            return one * two;
        }
    }

    static public int find(int[] numbers) {
        Optional<Integer> match = Arrays.stream(numbers)
                .boxed()
                .flatMap(one -> Arrays.stream(numbers).boxed().map(two -> new Pair(one, two)))
                .filter(Pair::isMatch)
                .map(Pair::product)
                .findFirst();

        return match.orElseThrow();
    }
}
