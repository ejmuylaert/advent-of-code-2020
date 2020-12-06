package org.ernstjan.advent.day4;

import java.util.List;
import java.util.Optional;

class Passport {
    private final int birthYear;
    private final int issueYear;
    private final int expirationYear;
    private final String height;
    private final String hairColor;
    private final String eyeColor;
    private final String passportId;
    private final Optional<String> countryId;

    Passport(int birthYear, int issueYear, int expirationYear, String height, String hairColor, String eyeColor, String passportId, Optional<String> countryId) {
        this.birthYear = birthYear;
        this.issueYear = issueYear;
        this.expirationYear = expirationYear;
        this.height = height;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.passportId = passportId;
        this.countryId = countryId;
    }

    boolean isValid() {
        return isValidBirthYear() &&
                isValidIssueYear() &&
                isValidExpirationYear() &&
                isValidHeight() &&
                isValidHairColor() &&
                isValidEyeColor() &&
                isValidPassportId();
    }

    /**
     * @return true if birth year at least 1920 and at most 2002.
     */
    private boolean isValidBirthYear() {
        return isNumberBetween(birthYear, 1920, 2002);
    }

    /**
     * @return true if issue year at least 2010 and at most 2020.
     */
    private boolean isValidIssueYear() {
        return isNumberBetween(issueYear, 2010, 2020);
    }

    /**
     * @return true if expiration year at least 2020 and at most 2030.
     */
    private boolean isValidExpirationYear() {
        return isNumberBetween(expirationYear, 2020, 2030);
    }

    /**
     * validates if below requirements are met:
     * - If cm, the number must be at least 150 and at most 193.
     * - If in, the number must be at least 59 and at most 76.
     *
     * @return true if height is valid
     */
    private boolean isValidHeight() {
        int value = Integer.parseInt(height, 0, height.length() - 2, 10);
        if (height.endsWith("cm")) {
            return isNumberBetween(value, 150, 193);
        } else {
            return isNumberBetween(value, 59, 76);
        }
    }

    /**
     * @return true if value is a # followed by exactly six characters 0-9 or a-f.
     */
    private boolean isValidHairColor() {
        if (!hairColor.startsWith("#")) return false;
        if (hairColor.length() != 7) return false;

        String hexNumber = hairColor.substring(1);
        try {
            Integer.parseInt(hexNumber, 16);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * @return true if value is one of amb blu brn gry grn hzl oth.
     */
    private boolean isValidEyeColor() {
        return List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                .contains(eyeColor);
    }

    /**
     * @return true if is is a nine-digit number, including leading zeroes
     */
    private boolean isValidPassportId() {
        int[] numbers = passportId.codePoints()
                .filter(Character::isDigit)
                .toArray();

        return numbers.length == 9;
    }

    private boolean isNumberBetween(int number, int low, int high) {
        return number >= low && number <= high;
    }
}
