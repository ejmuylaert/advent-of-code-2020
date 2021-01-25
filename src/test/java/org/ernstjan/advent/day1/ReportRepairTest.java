package org.ernstjan.advent.day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ReportRepairTest {

    @Test
    @DisplayName("Returns the product of the entries matching the target")
    void returnProductOfMatchingEntries() {
        ReportRepair reportRepair = new ReportRepair(42);
        Optional<Long> result = reportRepair.findPair(List.of(0, 2, 3, 4, 5, 6, 40));

        assertThat(result).contains(2 * 40L);
    }

    @Test
    @DisplayName("Returns the product of the entries matching the target (with 3 entries")
    void returnProductOfMatchingEntries3() {
        ReportRepair reportRepair = new ReportRepair(42);
        Optional<Long> result = reportRepair.findTriple(List.of(0, 2, 3, 4, 5, 6, 40));

        assertThat(result).contains(0L);
    }

    @Test
    @DisplayName("Returns empty when target sum not found")
    void returnEmptyWhenTargetNotFound() {
        ReportRepair reportRepair = new ReportRepair(42);
        Optional<Long> result = reportRepair.findPair(List.of(1, 2, 3, 4, 5, 6));

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Verify with given sample input")
    void testing() {
        ReportRepair reportRepair = new ReportRepair(2020);
        List<Integer> numbers = List.of(1721, 979, 366, 299, 675, 1456);

        assertThat(reportRepair.findPair(numbers)).contains(514579L);
        assertThat(reportRepair.findTriple(numbers)).contains(241861950L);
    }
}
