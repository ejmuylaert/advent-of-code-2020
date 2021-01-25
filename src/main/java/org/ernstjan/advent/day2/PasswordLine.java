package org.ernstjan.advent.day2;

import java.util.List;
import java.util.function.Function;

public class PasswordLine {
    private final int leftNumber;
    private final int rightNumber;
    private final char targetChar;
    private final String password;

    public PasswordLine(int leftNumber, int rightNumber, char targetChar, String password) {
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;
        this.targetChar = targetChar;
        this.password = password;
    }

    public static long countValid(List<PasswordLine> lines, Function<PasswordLine, Boolean> validator) {
        return lines.stream()
                .filter(validator::apply)
                .count();
    }

    public boolean isValidRental() {
        long count = password
                .chars()
                .filter(passwordChar -> passwordChar == targetChar)
                .count();

        return (count >= leftNumber && count <= rightNumber);
    }

    public boolean isValidToboggan() {
        boolean pos1match = password.charAt(leftNumber - 1) == targetChar;
        boolean pos2match = password.charAt(rightNumber - 1) == targetChar;

        return pos1match ^ pos2match;
    }

    @Override
    public String toString() {
        return "PasswordLine{" +
                "leftNumber=" + leftNumber +
                ", rightNumber=" + rightNumber +
                ", targetChar=" + targetChar +
                ", password='" + password + '\'' +
                '}';
    }
}
