package org.ernstjan.advent.day2;

import java.util.List;
import java.util.Scanner;

public class PasswordValidator {

    public static long countValid(List<String> lines) {
        return lines
                .stream()
                .filter(PasswordValidator::isValid)
                .count();
    }
    static boolean isValid(String line) {
        Scanner scanner = new Scanner(line);
        String bounds = scanner.next();
        char character = scanner.next().charAt(0);
        String password = scanner.next();

        Scanner boundsScanner = new Scanner(bounds).useDelimiter("-");
        int min = boundsScanner.nextInt();
        int max = boundsScanner.nextInt();

        long count = password.chars().filter(passwordChar -> passwordChar == character).count();

        return (count >= min && count <= max);
    }
}
