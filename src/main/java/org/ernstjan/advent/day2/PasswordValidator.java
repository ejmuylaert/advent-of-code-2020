package org.ernstjan.advent.day2;

import java.util.List;
import java.util.Scanner;

public class PasswordValidator {

    private static class PasswordLine {
        private final int numberOne;
        private final int numberTwo;
        private final char targetCharacter;
        private final String password;

        PasswordLine(String line) {
            Scanner scanner = new Scanner(line);
            String bounds = scanner.next();
            this.targetCharacter = scanner.next().charAt(0);
            this.password = scanner.next();

            Scanner boundsScanner = new Scanner(bounds).useDelimiter("-");
            this.numberOne = boundsScanner.nextInt();
            this.numberTwo = boundsScanner.nextInt();
        }

        boolean isValidRental() {
            long count = password
                    .chars()
                    .filter(passwordChar -> passwordChar == targetCharacter)
                    .count();

            return (count >= numberOne && count <= numberTwo);
        }

        boolean isValidToboggan() {
            boolean pos1match = password.charAt(numberOne - 1) == targetCharacter;
            boolean pos2match = password.charAt(numberTwo - 1) == targetCharacter;
            return pos1match ^ pos2match;
        }
    }

    public static long countValidRental(List<String> lines) {
        return lines
                .stream()
                .map(PasswordLine::new)
                .filter(PasswordLine::isValidRental)
                .count();
    }

    public static long countValidToboggan(List<String> lines) {
        return lines
                .stream()
                .map(PasswordLine::new)
                .filter(PasswordLine::isValidToboggan)
                .count();
    }

    static boolean isValidRental(String line) {
        return new PasswordLine(line).isValidRental();
    }

    static boolean isValidToboggan(String line) {
        return new PasswordLine(line).isValidToboggan();
    }
}
