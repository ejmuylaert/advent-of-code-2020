package org.ernstjan.advent.day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ReportRepairTest {

    @Test
    @DisplayName("Returns the product of the entries matching the target")
    void returnProductOfMatchingEntries() {
        ReportRepair reportRepair = new ReportRepair(42);
        Optional<Long> result = reportRepair.find(2, List.of(0, 2, 3, 4, 5, 6, 40));

        assertThat(result).contains(2 * 40L);
    }

    @Test
    @DisplayName("Returns the product of the entries matching the target (with 3 entries")
    void returnProductOfMatchingEntries3() {
        ReportRepair reportRepair = new ReportRepair(42);
        Optional<Long> result = reportRepair.find(3, List.of(0, 2, 3, 4, 5, 6, 40));

        assertThat(result).contains(0L);
    }

    @Test
    @DisplayName("Find is generalized, so also works with different number of entries")
    void differentNumberOfEntries() {
        ReportRepair reportRepair = new ReportRepair(100);

        assertThat(reportRepair.find(1, List.of(10, 20, 100))).contains(100L);
        assertThat(reportRepair.find(5, List.of(5, 10, 15, 20, 50))).contains(750000L);
    }

    @Test
    @DisplayName("Don't use duplicate numbers")
    void dontUseDuplicateNumbers() {
        ReportRepair reportRepair = new ReportRepair(100);
        // The only valid answer is using all these numbers, but if the numbers can
        // be duplicated, other answers are possible, like:
        // [5, 15, 15, 15, 50] -> 843750
        assertThat(reportRepair.find(5, List.of(5, 15, 20, 50, 10))).contains(750000L);
        assertThat(reportRepair.find(5, List.of(5, 10, 15, 20, 50))).contains(750000L);
    }

    @Test
    @DisplayName("Find throws error when number of entries < 1")
    void verifyEntriesLargerThenOne() {
        ReportRepair reportRepair = new ReportRepair(100);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> reportRepair.find(0, List.of(1, 2, 3)));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> reportRepair.find(-1, List.of(1, 2, 3)));
    }

    @Test
    @DisplayName("Find throws error when number of entries > input length")
    void verifyThatListIsBiggerThanNumberOfEntries() {
        ReportRepair reportRepair = new ReportRepair(100);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> reportRepair.find(4, List.of(1, 2, 3)));
    }

    @Test
    @DisplayName("Returns empty when target sum not found")
    void returnEmptyWhenTargetNotFound() {
        ReportRepair reportRepair = new ReportRepair(42);
        Optional<Long> result = reportRepair.find(2, List.of(1, 2, 3, 4, 5, 6));

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Verify with given sample input")
    void testing() {
        ReportRepair reportRepair = new ReportRepair(2020);
        List<Integer> numbers = List.of(1721, 979, 366, 299, 675, 1456);

        assertThat(reportRepair.find(2, numbers)).contains(514579L);
        assertThat(reportRepair.find(3, numbers)).contains(241861950L);
    }
}
