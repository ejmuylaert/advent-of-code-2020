package org.ernstjan.advent.day1;

import java.util.Arrays;
import java.util.List;
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

        public Triple triple(int three) {
            return new Triple(one, two, three);
        }
    }

    public static class Triple {
        private final int one;
        private final int two;
        private final int three;

        public Triple(int one, int two, int three) {
            this.one = one;
            this.two = two;
            this.three = three;
        }

        public boolean isMatch() {
            return (one + two + three) == 2020;
        }

        public long product() {
            return one * two * three;
        }
    }

    static public int find(List<Integer> numbers) {

        Optional<Integer> match = numbers.stream()
                .flatMap(one -> numbers.stream().map(two -> new Pair(one, two)))
                .filter(Pair::isMatch)
                .map(Pair::product)
                .findFirst();

        return match.orElseThrow();
    }

    static public long find_triple(List<Integer> numbers) {
        Optional<Long> match = numbers.stream()
                .flatMap(one ->
                        numbers.stream().map(two -> new Pair(one, two))
                                .flatMap(pair -> numbers.stream().map(pair::triple))
                )
                .filter(Triple::isMatch)
                .map(Triple::product)
                .findFirst();

        return match.orElseThrow();
    }
}
