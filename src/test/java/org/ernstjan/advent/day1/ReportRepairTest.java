package org.ernstjan.advent.day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportRepairTest {

     @Test
    void testing() {
         int[] numbers = {1721,
                 979,
                 366,
                 299,
                 675,
                 1456};

         assertEquals(ReportRepair.find(numbers), 514579);
     }
}
