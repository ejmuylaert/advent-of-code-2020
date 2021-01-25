package org.ernstjan.advent.day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class EntryTest {

    @Test
    @DisplayName("At least one number is required for Entries")
    void requireAtLeastOneEntry() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Entries(null));

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(Entries::new);
    }

    @ParameterizedTest
    @MethodSource("sums")
    @DisplayName("Check sum of entries")
    void checkSumOfEntries(Integer sum, List<Integer> numbers) {
        Entries entries = new Entries(numbers.toArray(Integer[]::new));
        assertThat(entries.sum()).isEqualTo(sum);
    }

    @ParameterizedTest
    @MethodSource("products")
    @DisplayName("Calculate product of entries")
    void calculateProduct(Long product, List<Integer> numbers) {
        Entries entries = new Entries(numbers.toArray(Integer[]::new));
        assertThat(entries.product()).isEqualTo(product);
    }

    static Stream<Arguments> sums() {
        return Stream.of(
                arguments(3, List.of(1, 2)),
                arguments(6, List.of(1, 2, 3)),
                arguments(42, List.of(42)));
    }

    static Stream<Arguments> products() {
        return Stream.of(
                arguments(2L, List.of(1, 2)),
                arguments(6L, List.of(1, 2, 3)),
                arguments(42L, List.of(42)),
                arguments(0L, List.of(100, 32, 0, 12)));
    }
}
