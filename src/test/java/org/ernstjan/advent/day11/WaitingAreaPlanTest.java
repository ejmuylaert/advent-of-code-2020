package org.ernstjan.advent.day11;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.ernstjan.advent.day11.Direction.*;
import static org.ernstjan.advent.day11.Tile.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WaitingAreaPlanTest {

    @Test
    void tilesAroundCoordinate() {
        Tile[][] layout = {
                {EMPTY, FLOOR, TAKEN, TAKEN, FLOOR},
                {EMPTY, EMPTY, FLOOR, TAKEN, TAKEN},
                {EMPTY, EMPTY, FLOOR, TAKEN, TAKEN}
        };

        WaitingAreaPlan plan = new WaitingAreaPlan(layout);
        Coordinate coordinate = new Coordinate(2, 1);

        assertEquals(Optional.of(TAKEN), plan.getTile(coordinate, NORTH));
        assertEquals(Optional.of(TAKEN), plan.getTile(coordinate, NORTH_EAST));
        assertEquals(Optional.of(TAKEN), plan.getTile(coordinate, EAST));
        assertEquals(Optional.of(TAKEN), plan.getTile(coordinate, SOUTH_EAST));
        assertEquals(Optional.of(FLOOR), plan.getTile(coordinate, SOUTH));
        assertEquals(Optional.of(EMPTY), plan.getTile(coordinate, SOUTH_WEST));
        assertEquals(Optional.of(EMPTY), plan.getTile(coordinate, WEST));
        assertEquals(Optional.of(FLOOR), plan.getTile(coordinate, NORTH_WEST));
    }

    @Test
    void missingTilesAroundCoordinate() {
        Tile[][] layout = {
                {EMPTY, FLOOR, TAKEN, TAKEN, FLOOR},
                {EMPTY, EMPTY, FLOOR, TAKEN, TAKEN},
                {EMPTY, EMPTY, FLOOR, TAKEN, TAKEN}
        };

        WaitingAreaPlan plan = new WaitingAreaPlan(layout);
        Coordinate coordinate = new Coordinate(2, 0);

        assertEquals(Optional.empty(), plan.getTile(coordinate, NORTH));
        assertEquals(Optional.empty(), plan.getTile(coordinate, NORTH_EAST));
        assertEquals(Optional.of(TAKEN), plan.getTile(coordinate, EAST));
        assertEquals(Optional.of(TAKEN), plan.getTile(coordinate, SOUTH_EAST));
        assertEquals(Optional.of(FLOOR), plan.getTile(coordinate, SOUTH));
        assertEquals(Optional.of(EMPTY), plan.getTile(coordinate, SOUTH_WEST));
        assertEquals(Optional.of(FLOOR), plan.getTile(coordinate, WEST));
        assertEquals(Optional.empty(), plan.getTile(coordinate, NORTH_WEST));
    }

    @Test
    void surroundings() {
        Tile[][] layout = {
                {EMPTY, FLOOR, TAKEN, TAKEN, FLOOR},
                {EMPTY, EMPTY, FLOOR, TAKEN, TAKEN},
                {EMPTY, EMPTY, FLOOR, TAKEN, TAKEN}
        };

        WaitingAreaPlan plan = new WaitingAreaPlan(layout);
        Coordinate coordinate = new Coordinate(2, 1);

        assertEquals(List.of(TAKEN, TAKEN, TAKEN, TAKEN, FLOOR, EMPTY, EMPTY, FLOOR),
                plan.surroundings(coordinate));
    }

    @Test
    void missingTilesSurroundings() {
        Tile[][] layout = {
                {EMPTY, FLOOR, TAKEN, TAKEN, FLOOR},
                {EMPTY, EMPTY, FLOOR, TAKEN, TAKEN},
                {EMPTY, EMPTY, FLOOR, TAKEN, TAKEN}
        };

        WaitingAreaPlan plan = new WaitingAreaPlan(layout);
        Coordinate coordinate = new Coordinate(2, 0);

        assertEquals(List.of(TAKEN, TAKEN, FLOOR, EMPTY, FLOOR), plan.surroundings(coordinate));
    }

    @Test
    void change() {
        Tile[][] layout = {
                {EMPTY, FLOOR, TAKEN, TAKEN, FLOOR},
                {EMPTY, EMPTY, TAKEN, TAKEN, TAKEN},
                {EMPTY, EMPTY, FLOOR, TAKEN, TAKEN}
        };

        WaitingAreaPlan plan = new WaitingAreaPlan(layout);

        assertEquals(TAKEN, change(plan, new Coordinate(0, 1)));
        assertEquals(Optional.empty(), plan.change(new Coordinate(1, 0)));
        assertEquals(Optional.empty(), plan.change(new Coordinate(2, 0)));
        assertEquals(EMPTY, change(plan, new Coordinate(3, 0)));
    }

    @Test
    void sampleUpdate() {
        WaitingAreaPlan before = WaitingAreaPlan.fromLines(List.of(
                "#.LL.L#.##",
                "#LLLLLL.L#",
                "L.L.L..L..",
                "#LLL.LL.L#",
                "#.LL.LL.LL",
                "#.LLLL#.##",
                "..L.L.....",
                "#LLLLLLLL#",
                "#.LLLLLL.L",
                "#.#LLLL.##"
        ));

        WaitingAreaPlan after = WaitingAreaPlan.fromLines(List.of(
                "#.##.L#.##",
                "#L###LL.L#",
                "L.#.#..#..",
                "#L##.##.L#",
                "#.##.LL.LL",
                "#.###L#.##",
                "..#.#.....",
                "#L######L#",
                "#.LL###L.L",
                "#.#L###.##"
        ));

        List<Change> changes = before.changes();
        assertEquals(after.toString(), before.update(changes).toString());
    }

    @Test
    void countTest() {
        WaitingAreaPlan plan = WaitingAreaPlan.fromLines(List.of(
                "#.#L.L#.##",
                "#LLL#LL.L#",
                "L.#.L..#..",
                "#L##.##.L#",
                "#.#L.LL.LL",
                "#.#L#L#.##",
                "..L.L.....",
                "#L#L##L#L#",
                "#.LLLLLL.L",
                "#.#L#L#.##"
        ));

        assertEquals(37, plan.numberOccupied());
    }

    @Test
    void sampleSimulate() {
        int result = WaitingAreaPlan.simulate(List.of(
                "L.LL.LL.LL",
                "LLLLLLL.LL",
                "L.L.L..L..",
                "LLLL.LL.LL",
                "L.LL.LL.LL",
                "L.LLLLL.LL",
                "..L.L.....",
                "LLLLLLLLLL",
                "L.LLLLLL.L",
                "L.LLLLL.LL"
        ));

        assertEquals(37, result);
    }

    @Test
    void allOccupiedIgnoreFloor() {
        WaitingAreaPlan plan = WaitingAreaPlan.fromLines(List.of(
                ".......#.",
                "...#.....",
                ".#.......",
                ".........",
                "..#L....#",
                "....#....",
                ".........",
                "#........",
                "...#....."
        ));

        Coordinate coordinate = new Coordinate(3, 4);

        assertEquals(Optional.of(TAKEN), plan.getVisibleSeat(coordinate, NORTH));
        assertEquals(Optional.of(TAKEN), plan.getVisibleSeat(coordinate, NORTH_EAST));
        assertEquals(Optional.of(TAKEN), plan.getVisibleSeat(coordinate, EAST));
        assertEquals(Optional.of(TAKEN), plan.getVisibleSeat(coordinate, SOUTH_EAST));
        assertEquals(Optional.of(TAKEN), plan.getVisibleSeat(coordinate, SOUTH));
        assertEquals(Optional.of(TAKEN), plan.getVisibleSeat(coordinate, SOUTH_WEST));
        assertEquals(Optional.of(TAKEN), plan.getVisibleSeat(coordinate, WEST));
        assertEquals(Optional.of(TAKEN), plan.getVisibleSeat(coordinate, NORTH_WEST));
    }

    @Test
    void oneEmptyIgnoreFloor() {
        WaitingAreaPlan plan = WaitingAreaPlan.fromLines(List.of(
                ".............",
                ".L.L.#.#.#.#.",
                "............."
        ));

        Coordinate coordinate = new Coordinate(1, 1);

        assertEquals(Optional.empty(), plan.getVisibleSeat(coordinate, NORTH));
        assertEquals(Optional.empty(), plan.getVisibleSeat(coordinate, NORTH_EAST));
        assertEquals(Optional.of(EMPTY), plan.getVisibleSeat(coordinate, EAST));
        assertEquals(Optional.empty(), plan.getVisibleSeat(coordinate, SOUTH_EAST));
        assertEquals(Optional.empty(), plan.getVisibleSeat(coordinate, SOUTH));
        assertEquals(Optional.empty(), plan.getVisibleSeat(coordinate, SOUTH_WEST));
        assertEquals(Optional.empty(), plan.getVisibleSeat(coordinate, WEST));
        assertEquals(Optional.empty(), plan.getVisibleSeat(coordinate, NORTH_WEST));
    }

    private Tile change(WaitingAreaPlan plan, Coordinate coordinate) {
        Change change = plan.change(coordinate).orElseThrow();

        return change.getNewValue();
    }
}