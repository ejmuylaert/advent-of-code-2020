package org.ernstjan.advent.day13;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartureTest {

    @Test
    void sample() {
        List<String> lines = List.of(
                "939",
                "7,13,x,x,59,x,31,19");

        assertEquals(295, Departure.answer(lines));

        System.out.println(Departure.answer("7,13"));
    }

    @Test
    void sampleDepartureTime() {
        assertEquals(1068781, Departure.answer("7,13,x,x,59,x,31,19"));
        assertEquals(754018, Departure.answer("67,7,59,61"));
        assertEquals(779210, Departure.answer("67,x,7,59,61"));
        assertEquals(1261476, Departure.answer("67,7,x,59,61"));
        assertEquals(1202161486, Departure.answer("1789,37,47,1889"));
    }

    @Test
    void nextDeparture() {
        assertEquals(931, Departure.next(929, 7));
        assertEquals(931, Departure.next(931, 7));
        assertEquals(936, Departure.next(929, 13));
    }
}
