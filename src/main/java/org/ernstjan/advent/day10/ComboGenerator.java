package org.ernstjan.advent.day10;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ComboGenerator {

    public static long generate(int size) {
        if (size == 1) return 1;
        if (size == 2) return 1;


        ArrayList<Integer> initialList = new ArrayList<>();
        initialList.add(1);

        return (int) generate(Stream.of(initialList), 2, size - 2)
                .map((ArrayList<Integer> l) -> {
                    l.add(size);
                    return l;
                })
                .filter(ComboGenerator::isValid)
                .count();

    }

    private static Stream<ArrayList<Integer>> generate(Stream<ArrayList<Integer>> currentStream, int targetNumber, int remaining) {
        if (remaining == 0) return currentStream;

        Stream<ArrayList<Integer>> updatedStream = currentStream.flatMap(l -> {
            ArrayList<Integer> withNumber = new ArrayList<>(l);
            ArrayList<Integer> withoutNumber = new ArrayList<>(l);

            withNumber.add(targetNumber);

            return Stream.of(withNumber, withoutNumber);
        });

        return generate(updatedStream, targetNumber + 1, remaining - 1);
    }

    private static boolean isValid(ArrayList<Integer> jolts) {
        Integer previous = jolts.get(0);

        for (int i = 1; i < jolts.size(); i++) {
            if (jolts.get(i) - previous > 3) {
                return false;
            }

            previous = jolts.get(i);
        }

        return true;
    }
}
