package org.ernstjan.advent.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WaitingAreaPlan {
    private final Tile[][] layout;

    public WaitingAreaPlan(Tile[][] layout) {
        this.layout = layout;
    }

    public static WaitingAreaPlan fromLines(List<String> lines) {
        List<Tile[]> grid = lines.stream().map(l -> {
            ArrayList<Tile> line = new ArrayList<>();
            for (Character c : l.toCharArray()) {
                line.add(Tile.forName(c));
            }
            return line.toArray(new Tile[]{});
        }).collect(Collectors.toList());

        Tile[][] layout = grid.toArray(new Tile[][]{});


        return new WaitingAreaPlan(layout);
    }

    public static int simulate(List<String> lines) {
        WaitingAreaPlan plan = WaitingAreaPlan.fromLines(lines);

        while (true) {
            List<Change> changes = plan.changes();
            if (changes.size() == 0) break;

            plan = plan.update(changes);
        }

        return plan.numberOccupied();
    }

    public static int simulateNoFloor(List<String> lines) {
        WaitingAreaPlan plan = WaitingAreaPlan.fromLines(lines);

        while (true) {
            List<Change> changes = plan.changesNoFloor();
            if (changes.size() == 0) break;

            plan = plan.update(changes);
        }

        return plan.numberOccupied();
    }

    public Optional<Tile> getTile(Coordinate coordinate, Direction direction) {
        return get(coordinate.neighbour(direction));
    }

    public Optional<Tile> getVisibleSeat(Coordinate coordinate, Direction direction) {
        Optional<Tile> tile = getTile(coordinate, direction);
        if (tile.equals(Optional.of(Tile.FLOOR))) {
            Coordinate nextCoordinate = coordinate.neighbour(direction);
            return getVisibleSeat(nextCoordinate, direction);
        } else {
            return tile;
        }
    }

    public List<Tile> surroundings(Coordinate coordinate) {
        return Arrays.stream(Direction.values())
                .flatMap(d -> getTile(coordinate, d).stream())
                .collect(Collectors.toList());
    }

    public List<Tile> surroundingsNoFloor(Coordinate coordinate) {
        return Arrays.stream(Direction.values())
                .flatMap(d -> getVisibleSeat(coordinate, d).stream())
                .collect(Collectors.toList());
    }

    public List<Change> changes() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[y].length; x++) {
                coordinates.add(new Coordinate(x, y));
            }
        }

        return coordinates.stream()
                .map(this::change)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    public List<Change> changesNoFloor() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[y].length; x++) {
                coordinates.add(new Coordinate(x, y));
            }
        }

        return coordinates.stream()
                .map(this::changeNoFloor)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    public Optional<Change> change(Coordinate coordinate) {
        Tile currentTile = layout[coordinate.getY()][coordinate.getX()];

        switch (currentTile) {
            case FLOOR:
                return Optional.empty();

            case EMPTY:
                if (surroundings(coordinate).stream().noneMatch(t -> t == Tile.TAKEN)) {
                    return Optional.of(new Change(coordinate, Tile.TAKEN));
                } else {
                    return Optional.empty();
                }

            case TAKEN:
                if (surroundings(coordinate).stream().filter(t -> t == Tile.TAKEN).count() >= 4) {
                    return Optional.of(new Change(coordinate, Tile.EMPTY));
                } else {
                    return Optional.empty();
                }

            default:
                throw new RuntimeException("Missing TILE case");
        }
    }

    public Optional<Change> changeNoFloor(Coordinate coordinate) {
        Tile currentTile = layout[coordinate.getY()][coordinate.getX()];

        switch (currentTile) {
            case FLOOR:
                return Optional.empty();

            case EMPTY:
                if (surroundingsNoFloor(coordinate).stream().noneMatch(t -> t == Tile.TAKEN)) {
                    return Optional.of(new Change(coordinate, Tile.TAKEN));
                } else {
                    return Optional.empty();
                }

            case TAKEN:
                if (surroundingsNoFloor(coordinate).stream().filter(t -> t == Tile.TAKEN).count() >= 5) {
                    return Optional.of(new Change(coordinate, Tile.EMPTY));
                } else {
                    return Optional.empty();
                }

            default:
                throw new RuntimeException("Missing TILE case");
        }
    }

    public WaitingAreaPlan update(List<Change> changes) {
        Tile[][] updated = this.layout.clone();
        changes.forEach(change -> {
            Coordinate coordinate = change.getCoordinate();
            updated[coordinate.getY()][coordinate.getX()] = change.getNewValue();
        });

        return new WaitingAreaPlan(updated);
    }

    public int numberOccupied() {
        int occupied = 0;
        for (Tile[] line : layout) {
            for (Tile tile : line) {
                if (tile == Tile.TAKEN) {
                    occupied++;
                }
            }
        }
        return occupied;
    }

    private Optional<Tile> get(Coordinate coordinate) {
        if (coordinate.getX() < 0) return Optional.empty();
        if (coordinate.getY() < 0) return Optional.empty();

        if (coordinate.getX() >= layout[0].length) return Optional.empty();
        if (coordinate.getY() >= layout.length) return Optional.empty();

        return Optional.of(layout[coordinate.getY()][coordinate.getX()]);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (Tile[] tiles : layout) {
            for (Tile tile : tiles) {
                buffer.append(tile);
            }
            buffer.append('\n');
        }

        return buffer.toString();
    }
}
