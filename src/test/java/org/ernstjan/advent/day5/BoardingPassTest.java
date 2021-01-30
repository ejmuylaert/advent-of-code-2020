package org.ernstjan.advent.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardingPassTest {
    @Test
    void sampleSeats() {
        BoardingPass boardingPass = new BoardingPass("FBFBBFFRLR");
        assertEquals(boardingPass.row(), 44);
        assertEquals(boardingPass.column(), 5);

        boardingPass = new BoardingPass("BFFFBBFRRR");
        assertEquals(boardingPass.row(), 70);
        assertEquals(boardingPass.column(), 7);
        assertEquals(boardingPass.id(), 567);

        boardingPass = new BoardingPass("FFFBBBFRRR");
        assertEquals(boardingPass.row(), 14);
        assertEquals(boardingPass.column(), 7);
        assertEquals(boardingPass.id(), 119);

        boardingPass = new BoardingPass("BBFFBBFRLL");
        assertEquals(boardingPass.row(), 102);
        assertEquals(boardingPass.column(), 4);
        assertEquals(boardingPass.id(), 820);
    }
}
