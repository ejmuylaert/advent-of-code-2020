package org.ernstjan.advent.day5;

import java.util.List;
import java.util.stream.Collectors;

public class BoardingPass {

    private final int row;
    private final int column;

    public static List<BoardingPass> fromCodes(List<String> codes) {
        return codes.stream()
                .map(BoardingPass::new)
                .collect(Collectors.toList());
    }

    public BoardingPass(String code) {
        row = partition(code.substring(0, 7), 127);
        column = partition(code.substring(7), 7);
    }

    private int partition(String code, int upperBound) {
        Range range = new Range(0, upperBound);
        for (char next_code : code.toCharArray()) {
            switch (next_code) {
                case 'F':
                case 'L':
                    range = range.lowerHalf();
                    break;
                case 'B':
                case 'R':
                    range = range.upperHalf();
                    break;
                default:
                    throw new IllegalArgumentException("Unrecognized code: " + next_code);
            }
        }

        return range.value();
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public int id() {
        return 8 * row + column;
    }
}
