package org.ernstjan.advent.day5;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Plane {

    private final LinkedList<Seat> seats = new LinkedList<>();

    public void addSeats(List<String> lines) {
        lines.forEach(l -> seats.add(new Seat(l)));
    }

    public int highestSeatId() {
        return seats
                .stream()
                .map(Seat::id)
                .reduce(0, Math::max);
    }

    public int availableSeat() {
        List<Seat> seats = this.seats
                .stream()
                .sorted(Comparator.comparingInt(Seat::id))
                .collect(Collectors.toList());


        Seat prev = seats.remove(0);
        for (Seat current: seats) {
            if (prev.id() + 2 == current.id()) {
                return prev.id() + 1;
            }
            prev = current;
        }

        throw new RuntimeException("No empty seat found!");
    }
}
