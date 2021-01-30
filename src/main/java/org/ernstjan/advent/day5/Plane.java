package org.ernstjan.advent.day5;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Plane {

    /**
     * Ordered list of seat ids;
     */
    private List<Integer> occupiedSeats = new LinkedList<>();

    public void board(List<BoardingPass> boardingPasses) {
        occupiedSeats = boardingPasses.stream()
                .map(BoardingPass::id)
                .sorted()
                .collect(Collectors.toList());
    }

    public long highestSeatId() {
        return occupiedSeats.get(occupiedSeats.size() - 1);
    }

    /**
     * Find the seat where both neighbouring seats are occupied.
     *
     * @return id of the available seat
     */
    public int availableSeat() {
        Integer previous = occupiedSeats.get(0);
        for (int i = 1; i < occupiedSeats.size(); i++) {
            Integer current = occupiedSeats.get(i);
            if (current - previous == 2) {
                return previous + 1;
            }
            previous = current;
        }

        throw new RuntimeException("No empty seat found!");
    }
}
