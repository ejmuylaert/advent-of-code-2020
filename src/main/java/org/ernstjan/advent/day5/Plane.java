package org.ernstjan.advent.day5;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Plane {

    private final LinkedList<BoardingPass> seats = new LinkedList<>();

    public void addSeats(List<String> lines) {
        lines.forEach(l -> seats.add(new BoardingPass(l)));
    }

    public int highestSeatId() {
        return seats
                .stream()
                .map(BoardingPass::id)
                .reduce(0, Math::max);
    }

    public int availableSeat() {
        List<BoardingPass> boardingPasses = this.seats
                .stream()
                .sorted(Comparator.comparingInt(BoardingPass::id))
                .collect(Collectors.toList());


        BoardingPass prev = boardingPasses.remove(0);
        for (BoardingPass current : boardingPasses) {
            if (prev.id() + 2 == current.id()) {
                return prev.id() + 1;
            }
            prev = current;
        }

        throw new RuntimeException("No empty seat found!");
    }
}
