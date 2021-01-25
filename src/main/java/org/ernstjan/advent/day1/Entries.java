package org.ernstjan.advent.day1;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class Entries {
    private final List<Integer> numbers;

    public Entries(Integer... numbers) {
        checkArgument(numbers.length > 0, "At least 1 entry required");

        this.numbers = Arrays.asList(numbers);
    }

    public int sum() {
        return numbers.stream().reduce(0, Integer::sum);
    }

    public long product() {
        return numbers.stream()
                .mapToLong(Integer::longValue)
                .reduce(1L, (a, b) -> a * b);
    }

    @Override
    public String toString() {
        return "Entries{" +
                "numbers=" + numbers +
                '}';
    }
}
