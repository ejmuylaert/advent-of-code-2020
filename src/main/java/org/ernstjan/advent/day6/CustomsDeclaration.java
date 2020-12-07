package org.ernstjan.advent.day6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class CustomsDeclaration {

    public int declare(List<String> lines) {
        LinkedList<HashSet<Character>> groups = new LinkedList<>();
        HashSet<Character> current = new HashSet<>();

        for (String line : lines) {
            if (line.isBlank()) {
                groups.add(current);
                current = new HashSet<>();
            }
            for (char c : line.toCharArray()) {
                current.add(c);
            }
        }
        if (!current.isEmpty()) {
            groups.add(current);
        }

        return groups
                .stream()
                .mapToInt(HashSet::size)
                .sum();
    }

    public long declareCommon(List<String> lines) {
        LinkedList<Long> groups = new LinkedList<>();
        HashMap<Character, Integer> current = new HashMap<>();

        int passengerCount = 0;
        for (String line : lines) {
            if (line.isBlank()) {
                final int currentCount = passengerCount;
                long count = current.entrySet().stream()
                        .filter(entry -> entry.getValue() == currentCount)
                        .count();

                groups.add(count);
                current = new HashMap<>();
                passengerCount = 0;
                continue;
            }
            for (char c : line.toCharArray()) {
                current.compute(c, (ch, i) -> {
                    if (i == null) return 1;
                    else return i + 1;
                });
            }
            passengerCount++;
        }
        if (!current.isEmpty()) {
            final int currentCount = passengerCount;
            long count = current.entrySet().stream()
                    .filter(entry -> entry.getValue() == currentCount)
                    .count();

            groups.add(count);
        }

        return groups
                .stream()
                .mapToLong(i -> i)
                .sum();
    }
}
