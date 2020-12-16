package org.ernstjan.advent.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Adapter {

    public static long calculate(List<String> lines) {
        long[] longs = lines.stream()
                .mapToLong(Long::parseLong)
                .sorted()
                .toArray();

        long prev = 0;
        long ones = 0;
        long twos = 0;
        long threes = 1; // adapter - device difference
        for (long current : longs) {
            long diff = current - prev;
            if (diff == 1) {
                ones++;
            } else if (diff == 2) {
                twos++;
            } else if (diff == 3) {
                threes++;
            }
            prev = current;
        }

        return ones * threes;
    }

    public static long combinations(List<String> lines) {
        long[] longs = lines.stream()
                .mapToLong(Long::parseLong)
                .sorted()
                .toArray();

        ArrayList<Long> currentGroup = new ArrayList<>();
        currentGroup.add(0L); // initial joltage


        long cs = 1;
        currentGroup = new ArrayList<>();
        currentGroup.add(0L);
        for (long aLong : longs) {
            if (aLong - Collections.max(currentGroup) == 1) {
                currentGroup.add(aLong);
            } else {
                long combos = ComboGenerator.generate(currentGroup.size());

                currentGroup = new ArrayList<>();
                currentGroup.add(aLong);

                cs *= combos;
            }
        }

        long combos = ComboGenerator.generate(currentGroup.size());
        cs *= combos;

        return cs;
    }
}
