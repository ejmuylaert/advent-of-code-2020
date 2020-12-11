package org.ernstjan.advent.day9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Decrypt {
    private final int lookback;
    private final List<Long> input;


    public Decrypt(List<Long> input, int lookback) {
        this.lookback = lookback;
        this.input = input;
    }

    public static Decrypt fromLines(List<String> lines, int lookback) {
        ArrayList<Long> input = new ArrayList<>();
        lines.forEach(l -> input.add(Long.parseLong(l)));

        return new Decrypt(input, lookback);
    }

    public long firstError() {
        List<Long> key = input.stream().limit(lookback).collect(Collectors.toList());
        List<Long> values = input.subList(lookback, input.size() - 1);

        for (long number : values) {
            if (isError(number, key)) {
                return number;
            }

            key.remove(0);
            key.add(number);
        }

        throw new RuntimeException("Did find fault");
    }

    private boolean isError(long number, List<Long> key) {
        for (int i = 0; i < key.size(); i++) {
            for (int j = i + 1; j < key.size(); j++) {
                if (key.get(i) + key.get(j) == number) {
                    return false;
                }
            }
        }

        return true;
    }

    public long weakness() {
        long target = firstError();

        List<Long> chain = weakChain(target);
        Long min = chain.stream().min(Comparator.comparingLong(x -> x)).orElseThrow();
        Long max = chain.stream().max(Comparator.comparingLong(x -> x)).orElseThrow();

        return min + max;
    }

    private List<Long> weakChain(long target) {
        for (int i = 1; i < input.size(); i++) {
            Long number = input.get(i - 1);
            ArrayList<Long> result = new ArrayList<>();
            result.add(number);
            for (int j = i; j < input.size(); j++) {
                result.add(input.get(j));
                number += input.get(j);
                if (number == target) {
                    return result;
                }
                if (number > target) {
                    break;
                }
            }
        }

        throw new RuntimeException("Chain not found");
    }
}
