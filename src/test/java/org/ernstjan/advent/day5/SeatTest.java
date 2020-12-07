package org.ernstjan.advent.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatTest {

    @Test
    void sampleSeats() {
        Seat seat = new Seat("FBFBBFFRLR");
        assertEquals(seat.row(), 44);
        assertEquals(seat.column(), 5);

        seat = new Seat("BFFFBBFRRR");
        assertEquals(seat.row(), 70);
        assertEquals(seat.column(), 7);
        assertEquals(seat.id(), 567);

        seat = new Seat("FFFBBBFRRR");
        assertEquals(seat.row(), 14);
        assertEquals(seat.column(), 7);
        assertEquals(seat.id(), 119);

        seat = new Seat("BBFFBBFRLL");
        assertEquals(seat.row(), 102);
        assertEquals(seat.column(), 4);
        assertEquals(seat.id(), 820);
    }
}
