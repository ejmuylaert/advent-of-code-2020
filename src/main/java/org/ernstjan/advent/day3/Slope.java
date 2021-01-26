package org.ernstjan.advent.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public class Slope {

    private final ArrayList<ArrayList<Boolean>> map;
    private final int width;

    public Slope(List<String> stringMap) {
        checkArgument(stringMap.size() > 0, "Map should have at least 1 line");

        width = stringMap.get(0).length();
        map = new ArrayList<>();

        for (String line : stringMap) {
            checkArgument(line.length() == width, "All lines should have the same length");

            ArrayList<Boolean> slopeLine = new ArrayList<>(line.length());
            for (int i = 0; i < line.length(); i++) {
                switch (line.charAt(i)) {
                    case '#':
                        slopeLine.add(true);
                        break;
                    case '.':
                        slopeLine.add(false);
                        break;

                    default:
                        throw new IllegalArgumentException("Unrecognized character: " + line.charAt(i));
                }
            }
            map.add(slopeLine);
        }
    }

    public Optional<Boolean> hasTreeAt(Position position) {
        checkArgument(position.getY() >= 0, "y should be >= 0, can't access above slope");

        if (position.getY() >= map.size()) {
            return Optional.empty();
        } else {
            int x = position.getX() % width;
            if (x < 0) x = width + x;

            return Optional.of(map.get(position.getY()).get(x));
        }
    }

    public int treeEncounter(Delta delta) {
        Toboggan toboggan = new Toboggan();

        int trees = 0;
        while (hasTreeAt(toboggan.getPosition()).isPresent()) {

            trees += hasTreeAt(toboggan.getPosition())
                    .map(isPresent -> isPresent ? 1 : 0)
                    .orElse(0);

            toboggan.move(delta);
        }
        return trees;
    }

    public long treeEncountersProduct(List<Delta> travels) {
        return travels.stream()
                .mapToLong(this::treeEncounter)
                .reduce(1, (a, b) -> a * b);
    }
}
