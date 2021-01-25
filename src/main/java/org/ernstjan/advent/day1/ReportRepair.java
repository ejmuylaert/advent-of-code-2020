package org.ernstjan.advent.day1;

import java.util.List;
import java.util.Optional;

public class ReportRepair {

    private final int targetSum;

    public ReportRepair(int targetSum) {
        this.targetSum = targetSum;
    }

    public Optional<Long> findPair(List<Integer> numbers) {
        return numbers.stream()
                .flatMap(one ->
                        numbers.stream().map(two ->
                                new Entries(one, two)))
                .filter(entries -> entries.sum() == targetSum)
                .map(Entries::product)
                .findFirst();
    }

    public Optional<Long> findTriple(List<Integer> numbers) {
        return numbers.stream()
                .flatMap(one ->
                        numbers.stream().flatMap(two ->
                                numbers.stream().map(three ->
                                        new Entries(one, two, three))))
                .filter(entries -> entries.sum() == targetSum)
                .map(Entries::product)
                .findFirst();
    }
}
