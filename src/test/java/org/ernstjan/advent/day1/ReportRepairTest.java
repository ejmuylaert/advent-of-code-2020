package org.ernstjan.advent.day1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportRepairTest {

     @Test
    void testing() {
         List<Integer> numbers = List.of(1721,
                 979,
                 366,
                 299,
                 675,
                 1456);

         assertEquals(ReportRepair.find(numbers), 514579);
     }
}
